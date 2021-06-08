package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummaryObject(
        val _status: Int?,
        val cardObject: Array<CardObject>?,
        val metadata: WalkSummaryObject?,
        val share_url: String?,
        val template: String?,
        val version: Int?,
) : Parcelable
