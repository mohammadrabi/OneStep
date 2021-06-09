package com.itg.onestep.summary

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.itg.onestep.R
import com.itg.onestep.databinding.ActivitySummaryBinding
import com.itg.onestep.modules.SummaryObject
import com.itg.onestep.modules.SummaryDetailsObject
import getJsonDataFromAsset
import org.json.JSONException

class SummaryActivity :
    AppCompatActivity(),
        SummaryCardButtonsClickListener {

    lateinit var summaryBinding: ActivitySummaryBinding
    lateinit var summaryEventHandler: SummaryEventHandler
    lateinit var summaryViewModel: SummaryViewModel
    lateinit var summaryObject: SummaryObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        try {
            val jsonFileString = getJsonDataFromAsset(applicationContext, "onestep_response.json")
            if (jsonFileString != null) {
                Log.i("data", jsonFileString)
                summaryObject = Gson().fromJson(jsonFileString, SummaryDetailsObject::class.java).summary
            }
        } catch (err: JSONException) {
        }
        summaryBinding = ActivitySummaryBinding.inflate(layoutInflater)
        summaryObject.let { it ->
            summaryViewModel = SummaryViewModel(
                it,
                this,
                summaryBinding, this
            )

            summaryEventHandler = SummaryEventHandler(
                it,
                this,
            )
            summaryBinding.eventHandler = summaryEventHandler
        }
        setContentView(summaryBinding.getRoot())
    }

    override fun onInfoClickedClicked(title: String?, description: String?) {
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(description)
                .setCancelable(true)
                .setPositiveButton(this.getString(R.string.yes_text)) { _, _ ->
                    // should finish the activity
                }
                .setNegativeButton(this.getString(R.string.cancel_text), null)
                .setIcon(R.mipmap.ic_launcher)
                .show()
    }
}
