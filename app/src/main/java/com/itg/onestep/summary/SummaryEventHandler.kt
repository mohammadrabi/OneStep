package com.itg.onestep.summary

import android.content.Intent
import com.itg.onestep.R
import com.itg.onestep.modules.SummaryObject
import com.itg.onestep.databinding.ActivityWalkSummaryBinding
import java.text.SimpleDateFormat
import java.util.Locale

class SummaryEventHandler(
    val uuid: String,
    var summary: SummaryObject,
    val activity: SummaryActivity,
    val activityBinding: ActivityWalkSummaryBinding?,
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

//    @ExperimentalCoroutinesApi
//    private suspend fun makeNetworkCall(url: String, params: RequestParams?) = Dispatchers.Default {
//        withContext(Dispatchers.IO) {
//            val networkHelper = NetworkHelper.getInstance(activity)
//            val responseHandler = NetworkHelper.MySyncJsonResponseHandler()
//            networkHelper.syncGet(url, params, responseHandler)
//            return@withContext responseHandler
//        }
//    }

    fun onBackButtonClicked() {
        activity.onBackPressed()
    }


    fun onShareButtonClicked() {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_TEXT, activity.getString(R.string.share_desc) + " " + summary.share_url)
        val shareIntent = Intent.createChooser(sendIntent, activity.getString(R.string.share_title))
        activity.startActivity(shareIntent)
    }

    fun onContinueClicked() {
        activity.onBackPressed()
    }

    fun onExplainThisToMeClicked() {
    }
}
