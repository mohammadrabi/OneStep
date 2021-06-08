package com.itg.onestep.handlers

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.itg.onestep.R
import com.itg.onestep.modules.CardObject
import com.itg.onestep.utils.IPreferenceHelper
import com.itg.onestep.utils.MeasureConverter
import com.itg.onestep.utils.PreferenceManager
import kotlin.math.roundToInt

class WalkAnalysisItemsViewHandler(
    private val context: Context,
    val cardObject: CardObject?
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
        shouldConvertToInches = "cm" == cardObject?.units && !usingMetricSystem
        shouldConvertToMiles = "km/h" == cardObject?.units && !usingMetricSystem
    }

    val iconColor: ColorStateList?
        get() {
            cardObject?.score?.let {
                return when (it) {
                    in 0..3 -> ContextCompat.getColorStateList(context, R.color.warm_pink)
                    in 4..7 -> ContextCompat.getColorStateList(context, R.color.yellow)
                    in 8..10 -> ContextCompat.getColorStateList(context, R.color.green)
                    else -> ContextCompat.getColorStateList(context, R.color.warm_pink)
                }
            }
         return ContextCompat.getColorStateList(context, R.color.warm_pink)
        }

    val title: String
        get() = cardObject?.title ?: ""

    val value: String
        get() {
            return when {
                cardObject?.stat_id == STRIDE_LENGTH -> {
                    if (getConvertedValue(cardObject.value) is Float) {
                        (getConvertedValue(cardObject.value) as Float).toInt().toString()
                    } else {
                        getConvertedValue(cardObject.value).toString()
                    }
                }
                cardObject?.stat_id == VELOCITY -> getSpeedValue(cardObject.value).toString()
                (cardObject?.value ?: 0f).toInt().compareTo(cardObject?.value ?: 0f) == 0 -> (cardObject?.value ?: 0f).toInt().toString()
                else -> cardObject?.value?.roundToDecimals(1).toString()
            }
        }

    val unitString: String
        get() {
            return when (cardObject?.stat_id) {
                STRIDE_LENGTH -> return getUnits()
                VELOCITY -> return getSpeedUnit()
                else -> cardObject?.units ?: ""
            }
        }

    private fun getConvertedValue(value: Float?): Any {
        return if (value != null) {
            return if (shouldConvertToInches) {
                val inches = MeasureConverter.cmToInches(value.toDouble())
                if (inches.toInt().compareTo(inches) == 0) inches.toInt() else inches.toFloat().roundToDecimals(1)
            } else if (value.toInt().compareTo(value) == 0) value.toInt() else value.roundToDecimals(1)
        } else 0
    }

    private fun getSpeedValue(value: Float?): Any {
        return if (value != null) {
            return if (shouldConvertToMiles) {
                val mph = MeasureConverter.kmToMph(value.toDouble())
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

    private fun getUnits(): String {
        return if (shouldConvertToInches) " in" else cardObject?.units ?: "cm"
    }

    private fun getSpeedUnit(): String {
        return if (shouldConvertToMiles) " mi/h" else cardObject?.units ?: "km/h"
    }

    companion object {
        const val STRIDE_LENGTH = "STRIDE_LENGTH"
        const val VELOCITY = "VELOCITY"
        const val HIP_RANGE = "HIP_RANGE"
    }
}
