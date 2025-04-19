package com.bitsbob.zeit

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var elapsed = 0
    private val updateInterval = 1000L
    private var runnable: Runnable? = null
    private var isTiming = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val timerText = findViewById<TextView>(R.id.timerText)

        timerText.setOnClickListener {
            if (isTiming) {
                stopStopwatch()
            } else {
                startStopwatch(timerText)
            }
        }
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }


    private fun startStopwatch(tv: TextView) {
        isTiming = true

        runnable = object : Runnable {
            override fun run() {
                if (isTiming) {
                    elapsed ++
                    tv.text = formatTime(elapsed)
                    tv.postDelayed(this, updateInterval)
                }
            }
        }

        tv.post(runnable!!)
    }

    private fun stopStopwatch() {
        isTiming = false
    }
}