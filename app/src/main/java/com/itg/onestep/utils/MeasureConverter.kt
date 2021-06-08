package com.itg.onestep.utils

class MeasureConverter {
    companion object {
        private const val CENTIMETERS_PER_INCH = 0.39370
        private const val METERS_PER_FOOT = 3.281
        private const val CENTIMETERS_PER_FOOT = 30.48
        private const val INCHES_PER_FOOT = 12
        private const val INCHES_PER_CM = 2.54
        private const val KM_PER_MILE = 0.6214
        fun cmToInches(cm: Double): Double {
            return cm * CENTIMETERS_PER_INCH
        }

        fun ftInchesToCm(ft: Double): Double {
            return ft * CENTIMETERS_PER_FOOT
        }

        fun ftToInches(ft: Double): Double {
            return ft * INCHES_PER_FOOT
        }

        fun mToFoot(m: Double): Double {
            return m * METERS_PER_FOOT
        }

        fun inchesToCm(inches: Double): Double {
            return inches * INCHES_PER_CM
        }

        fun kmToMph(km: Double): Double {
            return km * KM_PER_MILE
        }
    }
}
