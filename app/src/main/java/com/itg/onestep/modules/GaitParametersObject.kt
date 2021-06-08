package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GaitParametersObject(
    val gaitParameter: String?,
    val value: Float?,
    val units: String?,
    val score: Int?,
    val title: String?
) : Parcelable
