package com.itg.onestep.summary
import android.content.Context
import android.view.Gravity.TOP
import android.view.View
import com.itg.onestep.R
import com.itg.onestep.modules.CardObject
import com.itg.onestep.databinding.SummaryCardBinding
import com.itg.onestep.utils.MeasureConverter.Companion.cmToInches
import com.itg.onestep.utils.MeasureConverter.Companion.kmToMph
import com.itg.onestep.utils.IPreferenceHelper
import com.itg.onestep.utils.PreferenceManager
import com.itg.onestep.utils.roundToDecimals
import com.squareup.picasso.Picasso

class SummaryCardViewEventHandler(
        private val context: Context,
        private val cardObject: CardObject,
        binding: SummaryCardBinding?,
        private val summaryCardButtonsClickListener: SummaryCardButtonsClickListener?,
) {
    private var shouldConvertToInches: Boolean
    private var shouldConvertToMiles: Boolean
    private val preferenceHelper: IPreferenceHelper by lazy { PreferenceManager(context) }

    init {
        var usingMetricSystem = true
        when (preferenceHelper.getMeasureUnit()) {
            PreferenceManager.MeasureUnit.Imperial.ordinal -> {
                usingMetricSystem = false
            }
            PreferenceManager.MeasureUnit.Metric.ordinal -> {
                usingMetricSystem = true
            }
        }
        shouldConvertToInches = context.getString(R.string.cm_text) == cardObject.units && !usingMetricSystem
        shouldConvertToMiles = context.getString(R.string.kmh_text) == cardObject.units && !usingMetricSystem

        binding?.let {

            binding.infoView.setOnClickListener {
                summaryCardButtonsClickListener?.onInfoClickedClicked(cardObject.title,cardObject.info)
            }

            if (cardObject.stat_id == HIP_RANGE) {
                binding.resultUnitTextview.gravity = TOP
            }

            if (cardObject.value == null) {
                binding.rightContainer.visibility = View.GONE
                binding.rightNullContainer.visibility = View.VISIBLE
                binding.taskRateAmount.visibility = View.GONE
            } else {
                binding.rightNullContainer.visibility = View.GONE
            }
            cardObject.rainbow?.bubble_color?.let {
                when (it) {
                    "green" -> binding.bubbleContainer.setBackgroundResource(R.drawable.green_speech_bubble)
                    "orange" -> binding.bubbleContainer.setBackgroundResource(R.drawable.yellow_speech_bubble)
                    "red" -> binding.bubbleContainer.setBackgroundResource(R.drawable.red_speech_bubble)
                    else -> throw Exception("")
                }
            }

            cardObject.rainbow?.asset_url?.let {
                Picasso.get().setLoggingEnabled(true)
                Picasso.get()
                        .load(it)
                        .placeholder(R.drawable.rainbow_stride_length)
                        .error(R.drawable.rainbow_stride_length)
                        .into(binding.rangeBar);
            }
        }
    }

    companion object {
        const val STRIDE_LENGTH = "STRIDE_LENGTH"
        const val VELOCITY = "VELOCITY"
        const val HIP_RANGE = "HIP_RANGE"
    }

    val percent: Double
        get() = (cardObject.rainbow?.percent ?: 0.0) * 100

    val title: String
        get() = cardObject.title ?: ""

    val rateText: String
        get() {
            return when {
                cardObject.stat_id == STRIDE_LENGTH ->
                    if (shouldConvertToInches) cardObject.subtitle_en ?: ""
                    else cardObject.subtitle ?: ""
                cardObject.stat_id == VELOCITY ->
                    if (shouldConvertToMiles) cardObject.subtitle_en ?: ""
                    else cardObject.subtitle ?: ""
                else -> cardObject.subtitle ?: ""
            }
        }

    val endText: String
        get() {
            return when {
                cardObject.stat_id == STRIDE_LENGTH -> getConvertedValue(cardObject.rainbow?.end).toString()
                cardObject.stat_id == VELOCITY -> getSpeedValue(cardObject.rainbow?.end).toString()
                cardObject.rainbow?.end?.toInt()?.compareTo(cardObject.rainbow.end) == 0 -> cardObject.rainbow.end.toInt().toString()
                else -> cardObject.rainbow?.end?.roundToDecimals(1).toString()
            }
        }

    val startText: String
        get() {
            return when {
                cardObject.stat_id == STRIDE_LENGTH -> getConvertedValue(cardObject.rainbow?.start).toString()
                cardObject.stat_id == VELOCITY -> getSpeedValue(cardObject.rainbow?.start).toString()
                cardObject.rainbow?.start?.toInt()?.compareTo(cardObject.rainbow.start) == 0 -> cardObject.rainbow.start.toInt().toString()
                else -> cardObject.rainbow?.start?.roundToDecimals(1).toString()
            }
        }

    val value: String
        get() {
            return when {
                cardObject.stat_id == STRIDE_LENGTH -> {
                    if (getConvertedValue(cardObject.value) is Float) {
                        (getConvertedValue(cardObject.value) as Float).toInt().toString()
                    } else {
                        getConvertedValue(cardObject.value).toString()
                    }
                }
                cardObject.stat_id == VELOCITY -> getSpeedValue(cardObject.value).toString()
                cardObject.value?.toInt()?.compareTo(cardObject.value) == 0 -> cardObject.value.toInt().toString()
                else -> cardObject.value?.roundToDecimals(1).toString()
            }
        }

    val unitString: String
        get() {
            return when (cardObject.stat_id) {
                STRIDE_LENGTH -> return getUnits()
                VELOCITY -> return getSpeedUnit()
                else -> cardObject.units ?: ""
            }
        }

    private fun getUnits(): String {
        return if (shouldConvertToInches) context.getString(R.string.inch_text) else cardObject.units ?: context.getString(R.string.cm_text)
    }

    private fun getSpeedUnit(): String {
        return if (shouldConvertToMiles) context.getString(R.string.mile_text) else cardObject.units ?: context.getString(R.string.kmh_text)
    }

    private fun getConvertedValue(value: Float?): Any {
        return if (value != null) {
            return if (shouldConvertToInches) {
                val inches = cmToInches(value.toDouble())
                if (inches.toInt().compareTo(inches) == 0) inches.toInt() else inches.toFloat().roundToDecimals(1)
            } else if (value.toInt().compareTo(value) == 0) value.toInt() else value.roundToDecimals(1)
        } else 0
    }

    private fun getSpeedValue(value: Float?): Any {
        return if (value != null) {
            return if (shouldConvertToMiles) {
                val mph = kmToMph(value.toDouble())
                if (mph.toInt().compareTo(mph) == 0) mph.toInt() else mph.toFloat().roundToDecimals(1)
            } else if (value.toInt().compareTo(value) == 0) value.toInt() else value.roundToDecimals(1)
        } else {
            0
        }
    }
}
