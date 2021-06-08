package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WalkScore(val components_scores: List<ComponentsScore>, val value: Long) : Parcelable
