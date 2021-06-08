package com.itg.onestep.summary

import android.content.Context
import com.itg.onestep.R
import com.itg.onestep.modules.MetadataObject

class SummaryMetaDataEventHandler(private val context: Context, val metadata: MetadataObject?) {

    val time: String by lazy {
        val seconds = metadata?.seconds
        if (seconds != null) {
            val timeResult =
                "${(seconds / 60).toString().padStart(2, '0')}:" +
                    "${(seconds % 60).toString().padStart(2, '0')}" // it's not redundant - ignore the compiler
            timeResult
        } else {
            "--"
        }
    }

    val timeUnit: String by lazy {
        val seconds = metadata?.seconds
        if (seconds != null) {
            if (seconds / 60 >= 1) context.getString(R.string.min_text) else context.getString(R.string.sec_text)
        } else {
            ""
        }
    }

    val rate: String
        get() = (metadata?.steps ?: 0).toString()

    val rateUnit: String
        get() = context.getString(R.string.steps_text)
}
