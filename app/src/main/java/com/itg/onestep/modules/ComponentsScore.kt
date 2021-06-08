package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComponentsScore(
    val description: String?,
    val name: String?,
    val score: Float?,
    val weight: Long?
) : Parcelable
