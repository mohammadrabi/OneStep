package com.itg.onestep.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itg.onestep.R
import com.itg.onestep.databinding.ActivitySettingBinding
import com.itg.onestep.utils.IPreferenceHelper
import com.itg.onestep.utils.PreferenceManager

class SettingActivity : AppCompatActivity() {

    private val preferenceHelper: IPreferenceHelper by lazy { PreferenceManager(applicationContext) }
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.settings_title)
        when (preferenceHelper.getMeasureUnit()) {
            PreferenceManager.MeasureUnit.Imperial.ordinal -> {
                binding.radioGroup.check(R.id.imperial)
            }
            PreferenceManager.MeasureUnit.Metric.ordinal -> {
                binding.radioGroup.check(R.id.metric)
            }
        }

        binding.doneButton.setOnClickListener {
            when (binding.radioGroup.checkedRadioButtonId) {
                R.id.imperial -> {
                    preferenceHelper.setMeasureUnit(PreferenceManager.MeasureUnit.Imperial.ordinal)
                }
                R.id.metric -> {
                    preferenceHelper.setMeasureUnit(PreferenceManager.MeasureUnit.Metric.ordinal)
                }
            }
            finish()
        }

        binding.backArrowButton.setOnClickListener {
            finish()
        }
    }
}