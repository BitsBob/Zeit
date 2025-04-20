package com.bitsbob.zeit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bitsbob.zeit.databinding.PomodoroBinding

class PomodoroActivity : AppCompatActivity() {

    private lateinit var binding: PomodoroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PomodoroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val layout = findViewById<View>(R.id.root_layout)
        layout.isClickable = true
        layout.isFocusable = true

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
