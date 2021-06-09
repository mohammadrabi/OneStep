package com.itg.onestep.summary

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.itg.onestep.R
import com.itg.onestep.modules.SummaryObject
import com.itg.onestep.databinding.ActivitySummaryBinding
import com.itg.onestep.settings.SettingActivity
import com.itg.onestep.utils.timeStampToDate
import java.text.SimpleDateFormat
import java.util.*

class SummaryEventHandler(
    var summary: SummaryObject,
    private val activity: SummaryActivity,
) {

    val title: String
        get() {
            return summary.metadata?.title ?: ""
        }

    val time: String
        get() {
            return timeStampToDate(activity, summary.metadata?.timestamp)
        }

    fun onSettingsButtonClicked() {
        val intent = Intent(activity, SettingActivity::class.java)
        resultLauncher.launch(intent)
    }

    var resultLauncher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            reloadData()
        }
    }

    private fun reloadData() {
         activity.summaryViewModel.adapter.notifyDataSetChanged()
    }

    private fun showAlert(title: String?,message: String?) {
        AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(activity.getString(R.string.yes_text)) { _, _ ->

                }
                .setNegativeButton(activity.getString(R.string.cancel_text), null)
                .setIcon(R.mipmap.ic_launcher)
                .show()
    }

    fun onShareButtonClicked() {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_TEXT, summary.share_url)
        val shareIntent = Intent.createChooser(sendIntent, activity.getString(R.string.share_title))
        activity.startActivity(shareIntent)
    }

    fun onContinueClicked() {
        showAlert("OneStep","Continue button is pressed!")
    }

    fun onFeedBackButtonClicked() {
        showAlert("OneStep","FeedBack button is pressed!")
    }
}
