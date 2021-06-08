package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummaryDetailsObject(
        val summary: SummaryObject,
        val summary_template: String
) : Parcelable
