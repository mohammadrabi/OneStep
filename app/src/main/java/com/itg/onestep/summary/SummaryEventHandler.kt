package com.itg.onestep.summary

import android.content.Intent
import com.itg.onestep.R
import com.itg.onestep.modules.SummaryObject
import com.itg.onestep.databinding.ActivitySummaryBinding
import com.itg.onestep.settings.SettingActivity
import java.text.SimpleDateFormat
import java.util.Locale

class SummaryEventHandler(
        val uuid: String,
        var summary: SummaryObject,
        val activity: SummaryActivity,
        val activityBinding: ActivitySummaryBinding?,
) {

    val title: String
        get() {
            return summary.metadata?.title ?: ""
        }

    val time: String
        get() {
            val simpleDateFormat = SimpleDateFormat(activity.getString(R.string.date_format), Locale.ENGLISH)
            return simpleDateFormat.format((summary.metadata?.timestamp ?: 0))

        }

    fun onSettingsButtonClicked() {
        val intent = Intent(activity, SettingActivity::class.java)
        activity.startActivity(intent)
    }

    fun onShareButtonClicked() {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_TEXT, summary.share_url)
        val shareIntent = Intent.createChooser(sendIntent, activity.getString(R.string.share_title))
        activity.startActivity(shareIntent)
    }

    fun onContinueClicked() {

    }

    fun onFeedBackButtonClicked() {

    }
}
