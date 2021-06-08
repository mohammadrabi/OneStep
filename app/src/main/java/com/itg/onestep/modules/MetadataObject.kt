package com.itg.onestep.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MetadataObject(
        val extraction_level: Int?,
        val seconds: Int,
        val steps: Int,
        val taskName: String?,
        val taskTag: String?,
        val timestamp: Int?,
        val title: String?,
        val uuid: String?,
        val label: String?,
        var note: String?
) : Parcelable
