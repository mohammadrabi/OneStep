package com.itg.onestep.summary

import android.content.Context
import com.itg.onestep.R
import com.itg.onestep.modules.MetadataObject
import com.itg.onestep.utils.timeUnit
import com.itg.onestep.utils.timeWithPadFormat

class SummaryMetaDataEventHandler(private val context: Context, private val metadata: MetadataObject?) {

    val time: String by lazy {
        val seconds = metadata?.seconds
        val timeResult = timeWithPadFormat(seconds)
        timeResult
    }

    val timeUnit: String by lazy {
        val seconds = metadata?.seconds
        val timeUnit = timeUnit(context,seconds)
        timeUnit
    }

    val rate: String
        get() = (metadata?.steps ?: 0).toString()

    val rateUnit: String
        get() = " " + context.getString(R.string.steps_text)
}
