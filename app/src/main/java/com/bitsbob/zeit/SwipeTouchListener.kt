package com.bitsbob.zeit

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class SwipeGestureListener(
    private val activity: AppCompatActivity,
    private val onSwipeLeft: () -> Unit = {},
    private val onSwipeRight: () -> Unit = {},
    private val onSwipeUp: () -> Unit = {},
    private val onSwipeDown: () -> Unit = {}
) : View.OnTouchListener {

    private val gestureDetector = GestureDetector(activity, GestureListener())

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return event?.let { gestureDetector.onTouchEvent(it) } ?: false
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffX = e2.x - e1!!.x
            val diffY = e2.y - e1.y
            return when {
                abs(diffX) > abs(diffY) -> {
                    if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) onSwipeRight() else onSwipeLeft()
                        true
                    } else false
                }
                abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD -> {
                    if (diffY > 0) onSwipeDown() else onSwipeUp()
                    true
                }
                else -> false
            }
        }
    }
}
