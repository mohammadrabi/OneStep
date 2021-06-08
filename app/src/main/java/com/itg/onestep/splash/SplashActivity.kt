package com.itg.onestep.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.itg.onestep.R
import com.itg.onestep.summary.SummaryActivity

class SplashActivity : AppCompatActivity() {
    // After 1500 mileSeconds / 1.5 seconds your next activity will display.
    private var timeOut:Long = 1500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplashScreen()
    }
    private fun loadSplashScreen(){
        Handler(Looper.getMainLooper()).postDelayed({
            // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
            val intent = Intent(this,SummaryActivity::class.java)
            startActivity(intent)
            finish()
        }, timeOut)
    }
}