package com.itg.onestep.utils

import android.content.Context
import com.itg.onestep.R
import java.text.SimpleDateFormat
import java.util.*

fun timeStampToDate(context: Context, timestamp: Int?): String {
    val simpleDateFormat = SimpleDateFormat(context.getString(R.string.date_format), Locale.ENGLISH)
    val date = Date((timestamp ?: 0).toLong() * 1000)
    return simpleDateFormat.format(date)
}

fun timeWithPadFormat( seconds: Int?): String {
    var time = "--"
    if (seconds != null) {
        time = "${(seconds / 60).toString().padStart(2, '0')}:" +
                "${(seconds % 60).toString().padStart(2, '0')}"
    }
    return time
}

fun timeUnit(context: Context,seconds: Int?): String {
    var timeUnit = ""
    if (seconds != null) {
        if (seconds / 60 >= 1)
            timeUnit = " " + context.getString(R.string.min_text) else " " + context.getString(R.string.sec_text)
    }
    return timeUnit
}