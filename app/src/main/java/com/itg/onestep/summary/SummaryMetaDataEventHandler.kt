package com.itg.onestep.summary

import com.itg.onestep.modules.MetadataObject

class SummaryMetaDataEventHandler(val metadata: MetadataObject?) {

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
            if (seconds / 60 >= 1) "min" else "sec"
        } else {
            ""
        }
    }

    val rate: String
        get() = (metadata?.steps ?: 0).toString()

    val rateUnit: String
        get() = " Steps"
}
