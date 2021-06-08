package com.itg.onestep.summary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.itg.onestep.R
import com.itg.onestep.databinding.ActivityWalkSummaryBinding
import com.itg.onestep.listener.CalibrationButtonsClickListener
import com.itg.onestep.modules.SummaryObject
import com.itg.onestep.settings.SettingActivity


class SummaryActivity :
    AppCompatActivity(),
    CalibrationButtonsClickListener{

    lateinit var summaryBinding: ActivityWalkSummaryBinding
    lateinit var summaryEventHandler: SummaryEventHandler
    lateinit var summaryViewModel: SummaryViewModel
    private var fromRecorder: Boolean = false
    lateinit var summaryObject: SummaryObject
    lateinit var uuid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        fromRecorder = getFromRecorder(intent.extras) ?: false
//        try {
//            val jsonStr = getSummary(intent.extras)
//            summaryObject = Gson().fromJson(jsonStr, SummaryObject::class.java)
//            uuid = summaryObject.metadata?.uuid ?: ""
//        } catch (err: JSONException) {
//        }
//        try {
//
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        var parameterList: ArrayList<String> = ArrayList()
//        var extractedParamsCount = 0
//        summaryBinding = ActivityWalkSummaryBinding.inflate(layoutInflater)
//        summaryObject.let { it ->
//            it.CardObject?.forEach { card ->
//                parameterList.add(card.title ?: "")
//                if (card.value != null) {
//                    extractedParamsCount += 1
//                }
//            }
//
//            summaryViewModel = SummaryViewModel(
//                it,
//                this,
//                summaryBinding, this
//            )
//
//            summaryEventHandler = SummaryEventHandler(
//                uuid,
//                it,
//                this,
//                summaryBinding,
//            )
//            summaryBinding.eventHandler = summaryEventHandler
//        }

//        setContentView(summaryBinding.getRoot())
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

    companion object {
        const val SUMAMRY_JSON = "SUMMARY_JSON"
        const val IS_FROM_COURSE_WALK = "is_from_course_walk"
        const val FROM_RECORDER_KEY = "FROM_RECORDER"
        const val POPUP_KEY = "Summary_GenericPopup"
        val TAG = SummaryActivity::class.java.simpleName
        fun getSummary(arguments: Bundle?) = arguments?.getString(SUMAMRY_JSON)
        fun getFromRecorder(arguments: Bundle?) = arguments?.getBoolean(FROM_RECORDER_KEY)
        fun getIsFromCourse(arguments: Bundle?) = arguments?.getBoolean(IS_FROM_COURSE_WALK)
        fun getIntentForSummary(
            context: Context,
            summary: String,
            isfromRecorder: Boolean,
            isFromCourse: Boolean
        ): Intent {
            val intent = Intent(context, SummaryActivity::class.java)
            intent.putExtra(SUMAMRY_JSON, summary)
            intent.putExtra(FROM_RECORDER_KEY, isfromRecorder)
            intent.putExtra(IS_FROM_COURSE_WALK, isFromCourse)
            return intent
        }
    }

}
