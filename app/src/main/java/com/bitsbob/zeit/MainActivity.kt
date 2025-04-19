package com.bitsbob.zeit

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    private var elapsed = 0
    private val updateInterval = 1000L
    private var runnable: Runnable? = null
    private var isTiming = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val settingsButton = findViewById<MaterialButton>(R.id.settingsButton)
        settingsButton.setOnClickListener {
            showSettingsBottomSheet()
        }
        val timerText = findViewById<TextView>(R.id.timerText)

        timerText.setOnClickListener {
            if (isTiming) {
                stopStopwatch()
            } else {
                startStopwatch(timerText)
            }
        }
    }

    private fun showSettingsBottomSheet() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_settings, null)

        val switch = view.findViewById<SwitchMaterial>(R.id.darkModeSwitch)
        val closeBtn = view.findViewById<Button>(R.id.closeButton)

        switch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Dark Mode: $isChecked", Toast.LENGTH_SHORT).show()
        }

        closeBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()
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