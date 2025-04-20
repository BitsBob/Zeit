package com.bitsbob.zeit

import android.content.Intent
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bitsbob.zeit.databinding.IntervalTimerBinding

class IntervalTimerActivity : AppCompatActivity() {

    private lateinit var binding: IntervalTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IntervalTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val layout = findViewById<View>(R.id.root_layout)
        val timerText = findViewById<TextView>(R.id.timerText)

        layout.isClickable = true
        layout.isFocusable = true

        timerText.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }

        layout.setOnTouchListener(
            SwipeGestureListener(
                this,
                onSwipeLeft = {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) },
            )
        )
    }
}
