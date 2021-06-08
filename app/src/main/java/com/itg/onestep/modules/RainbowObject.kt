package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RainbowObject(
        val asset_id: String?,
        val asset_url: String?,
        val bubble_color: String?,
        val end: Float?,
        val percent: Double?,
        val start: Float?,
        val type: String?,
        val units: String?,
        val value: Float?,
        val template: String?,
        val version: Int?
) : Parcelable
