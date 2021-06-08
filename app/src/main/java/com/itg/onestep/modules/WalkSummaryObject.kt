package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class WalkSummaryObject(
    val uuid: String?,
    val title: String?,
    val timestamp: String?,
    val steps: Int,
    val duration: String?,
    val seconds: Int,
    val result: List<GaitParametersObject>?
) : Parcelable
