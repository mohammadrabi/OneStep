package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardObject(
        val description: String?,
        val rainbow: RainbowObject?,
        val stat_id: String?,
        val title: String?,
        val type: String?,
        val units: String?,
        val value: Float?,
        val subtitle: String?,
        val gait_parameter: String?,
        val info: String?,
        val subtitle_en: String?,
        val tag: String?,
        val template: String?,
        val version: Int?,
        val youtube_id: String?,
        val trend_available: Boolean?,
        val score: Int?
) : Parcelable
