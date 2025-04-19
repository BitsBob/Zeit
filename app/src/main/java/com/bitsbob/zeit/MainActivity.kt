package com.bitsbob.zeit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

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
        val resetBtn = findViewById<MaterialButton>(R.id.resetBtn)
        val layout = findViewById<View>(R.id.root_layout)
        layout.isClickable = true
        layout.isFocusable = true

        layout.setOnTouchListener(
            SwipeGestureListener(
                this,
                onSwipeRight = {
                    val intent = Intent(this, PomodoroActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right) },
            )
        )


        timerText.setOnClickListener {
            if (isTiming) {
                stopStopwatch()
            } else {
                startStopwatch(timerText)
            }
        }

        resetBtn.setOnClickListener {
            if (!isTiming) {
                elapsed = 0
                timerText.text = formatTime(elapsed)
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