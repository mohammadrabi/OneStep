package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RainbowObject(
    val asset_id: String?, // ":"rainbow_balance",
    val asset_url: String?,
    val bubble_color: String?, // ":"green",
    val end: Float?,
    val percent: Double?,
    val start: Float?, // negative numbers?
    val type: String?, // simple",
    val units: String?,
    val value: Float?,
    val template: String?,
    val version: Int?
) : Parcelable
