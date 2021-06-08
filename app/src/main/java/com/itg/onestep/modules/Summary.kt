package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Summary(
    val _status: Int?,
    val cards: Array<cards>?,
    val gps: Gps?,
    var insight: Insight?,
    val metadata: MetadataObject?,
    val share_url: String?,
    val template: String?,
    val version: Int?,
    val walkScore: WalkScore?
) : Parcelable
