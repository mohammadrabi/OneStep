package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummaryDetails(
    val summary: Summary,
    val summary_template: String
) : Parcelable
