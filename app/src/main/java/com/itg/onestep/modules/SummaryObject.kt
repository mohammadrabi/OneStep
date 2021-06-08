package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummaryObject(
        val _status: Int?,
        val cards: ArrayList<CardObject>?,
        val metadata: MetadataObject?,
        val share_url: String?,
        val template: String?,
        val version: Int?,
) : Parcelable
