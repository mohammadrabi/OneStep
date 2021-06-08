package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WalkMetadataObject(
    val uuid: String?,
    val title: String?,
    val steps: Int,
    val seconds: Int,
    ) : Parcelable
