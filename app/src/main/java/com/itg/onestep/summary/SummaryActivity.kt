package com.itg.onestep.summary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.itg.onestep.R
import com.itg.onestep.databinding.ActivityWalkSummaryBinding
import com.itg.onestep.listener.SummaryCardButtonsClickListener
import com.itg.onestep.modules.Summary
import com.itg.onestep.modules.SummaryDetails
import com.itg.onestep.settings.SettingActivity
import getJsonDataFromAsset
import org.json.JSONException


class SummaryActivity :
    AppCompatActivity(),
    SummaryCardButtonsClickListener{

    lateinit var summaryBinding: ActivityWalkSummaryBinding
    lateinit var summaryEventHandler: SummaryEventHandler
    lateinit var summaryViewModel: SummaryViewModel
    private var fromRecorder: Boolean = false
    lateinit var summaryObject: Summary
    lateinit var uuid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val jsonFileString = getJsonDataFromAsset(applicationContext, "onestep_response.json")
            if (jsonFileString != null) {
                Log.i("data", jsonFileString)
            }

            summaryObject = Gson().fromJson(jsonFileString, SummaryDetails::class.java).summary
            Log.i("data", " " +  summaryObject)

            uuid = summaryObject.metadata?.uuid ?: ""
        } catch (err: JSONException) {
        }
        try {

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        var parameterList: ArrayList<String> = ArrayList()
        var extractedParamsCount = 0
        summaryBinding = ActivityWalkSummaryBinding.inflate(layoutInflater)
        summaryObject.let { it ->
            it.cards?.forEach { card ->
                parameterList.add(card.title ?: "")
                if (card.value != null) {
                    extractedParamsCount += 1
                }
            }
//
            summaryViewModel = SummaryViewModel(
                it,
                this,
                summaryBinding, this
            )
//
            summaryEventHandler = SummaryEventHandler(
                uuid,
                it,
                this,
                summaryBinding,
            )
            summaryBinding.eventHandler = summaryEventHandler
        }

        setContentView(summaryBinding.getRoot())
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onMoreInfoClickedClicked(title: String?, description: String?, videoId: String?) {

    }

    override fun onViewDetailsClicked(
        title: String?,
        gaitParameter: String?,
        uuid: String?,
        seconds: Int
    ) {

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.summary_menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_button -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
