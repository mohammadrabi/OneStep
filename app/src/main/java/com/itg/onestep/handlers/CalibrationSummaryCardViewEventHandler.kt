package com.itg.onestep.handlers
import android.content.Context
import android.view.Gravity.TOP
import android.view.View
import com.bumptech.glide.Glide
import com.itg.onestep.R
import com.itg.onestep.modules.CardObject
import com.itg.onestep.databinding.CalibrationSummaryCardBinding
import com.itg.onestep.utils.MeasureConverter.Companion.cmToInches
import com.itg.onestep.utils.MeasureConverter.Companion.kmToMph
import com.itg.onestep.listener.CalibrationButtonsClickListener
import com.itg.onestep.utils.IPreferenceHelper
import com.itg.onestep.utils.PreferenceManager
import kotlin.math.roundToInt

class CalibrationSummaryCardViewEventHandler(
    private val context: Context,
    private val cardObject: CardObject,
    binding: CalibrationSummaryCardBinding?,

    private val calibrationButtonsClickListener: CalibrationButtonsClickListener?,
    private val seconds: Int?
) {
    private var shouldConvertToInches: Boolean
    private var shouldConvertToMiles: Boolean
    private val preferenceHelper: IPreferenceHelper by lazy { PreferenceManager(context) }

    init {
        // set percentage
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
                val description = "Test test"
                calibrationButtonsClickListener?.onMoreInfoClickedClicked(cardObject.title, description, cardObject.youtube_id)
            }
            binding.viewDetails.setOnClickListener {
                viewDetailsButtonClicked()
            }

            if (cardObject.stat_id == WalkAnalysisItemsViewHandler.HIP_RANGE) {
                binding.resultUnitTextview.gravity = TOP
            }

            if (cardObject.value == null) {
                binding.rightContainer.visibility = View.GONE
                binding.rightNullContainer.visibility = View.VISIBLE
                binding.taskRateAmount.visibility = View.GONE
            } else {
                binding.rightNullContainer.visibility = View.GONE
            }
            cardObject.RainbowObject?.bubble_color?.let {
                when (it) {
                    "green" -> binding.bubbleContainer.setBackgroundResource(R.drawable.green_speech_bubble)
                    "orange" -> binding.bubbleContainer.setBackgroundResource(R.drawable.yellow_speech_bubble)
                    "red" -> binding.bubbleContainer.setBackgroundResource(R.drawable.red_speech_bubble)
                    else -> throw Exception("")
                }
            }

            cardObject.RainbowObject?.asset_url?.let {
                Glide.with(binding.rangeBar.context)
                    .load(it)
                    .dontAnimate()
                    .error(Glide.with(binding.rangeBar).load(R.drawable.rainbow_stride_length))
                    .into(binding.rangeBar)
            }
        }
    }

    private fun viewDetailsButtonClicked() {
        calibrationButtonsClickListener?.onViewDetailsClicked(cardObject.title, cardObject.gait_parameter, "", seconds = seconds ?: 0)
    }
    val percent: Double
        get() = (cardObject.RainbowObject?.percent ?: 0.0) * 100

    val title: String
        get() = cardObject.title ?: ""

    val rateText: String
        get() {
            return when {
                cardObject.stat_id == WalkAnalysisItemsViewHandler.STRIDE_LENGTH ->
                    if (shouldConvertToInches) cardObject.subtitle_en ?: ""
                    else cardObject.subtitle ?: ""
                cardObject.stat_id == WalkAnalysisItemsViewHandler.VELOCITY ->
                    if (shouldConvertToMiles) cardObject.subtitle_en ?: ""
                    else cardObject.subtitle ?: ""
                else -> cardObject.subtitle ?: ""
            }
        }

    val endText: String
        get() {
            return when {
                cardObject.stat_id == WalkAnalysisItemsViewHandler.STRIDE_LENGTH -> getConvertedValue(cardObject.RainbowObject?.end).toString()
                cardObject.stat_id == WalkAnalysisItemsViewHandler.VELOCITY -> getSpeedValue(cardObject.RainbowObject?.end).toString()
                cardObject.RainbowObject?.end?.toInt()?.compareTo(cardObject.RainbowObject.end) == 0 -> cardObject.RainbowObject.end.toInt().toString()
                else -> cardObject.RainbowObject?.end?.roundToDecimals(1).toString()
            }
        }

    val startText: String
        get() {
            return when {
                cardObject.stat_id == WalkAnalysisItemsViewHandler.STRIDE_LENGTH -> getConvertedValue(cardObject.RainbowObject?.start).toString()
                cardObject.stat_id == WalkAnalysisItemsViewHandler.VELOCITY -> getSpeedValue(cardObject.RainbowObject?.start).toString()
                cardObject.RainbowObject?.start?.toInt()?.compareTo(cardObject.RainbowObject.start) == 0 -> cardObject.RainbowObject.start.toInt().toString()
                else -> cardObject.RainbowObject?.start?.roundToDecimals(1).toString()
            }
        }

    val value: String
        get() {
            return when {
                cardObject.stat_id == WalkAnalysisItemsViewHandler.STRIDE_LENGTH -> {
                    if (getConvertedValue(cardObject.value) is Float) {
                        (getConvertedValue(cardObject.value) as Float).toInt().toString()
                    } else {
                        getConvertedValue(cardObject.value).toString()
                    }
                }
                cardObject.stat_id == WalkAnalysisItemsViewHandler.VELOCITY -> getSpeedValue(cardObject.value).toString()
                cardObject.value?.toInt()?.compareTo(cardObject.value) == 0 -> cardObject.value.toInt().toString()
                else -> cardObject.value?.roundToDecimals(1).toString()
            }
        }

    val unitString: String
        get() {
            return if (cardObject.stat_id == WalkAnalysisItemsViewHandler.STRIDE_LENGTH)
                return getUnits()
            else if (cardObject.stat_id == WalkAnalysisItemsViewHandler.VELOCITY)
                return getSpeedUnit()
            else
                cardObject.units ?: ""
        }

    private fun getUnits(): String {
        return if (shouldConvertToInches) " in" else cardObject.units ?: "cm"
    }

    private fun getSpeedUnit(): String {
        return if (shouldConvertToMiles) " mi/h" else cardObject.units ?: "km/h"
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

    fun Float.roundToDecimals(decimals: Int): Float {
        var dotAt = 1
        repeat(decimals) { dotAt *= 10 }
        val roundedValue = (this * dotAt).roundToInt()
        return (roundedValue / dotAt) + (roundedValue % dotAt).toFloat() / dotAt
    }
}
