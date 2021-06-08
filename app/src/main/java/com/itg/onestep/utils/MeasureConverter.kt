package com.itg.onestep.utils

class MeasureConverter {
    companion object {
        private const val CENTIMETERS_PER_INCH = 0.39370
        private const val KM_PER_MILE = 0.6214
        fun cmToInches(cm: Double): Double {
            return cm * CENTIMETERS_PER_INCH
        }

        fun kmToMph(km: Double): Double {
            return km * KM_PER_MILE
        }
    }
}
