package com.afra55.httpforus.u

import android.animation.ValueAnimator
import android.app.Activity
import com.afra55.httpforus.WeHelp

/**
 * @author Afra55
 * @date 2019-07-26
 * A smile is the best business card.
 */
object WeHelpScreenUtil {


    @JvmStatic
    fun dp2px(dipValue: Float): Int {
        return WeHelp.context?.let {
            val density = it.applicationContext.resources.displayMetrics.density
            dipValue * density + 0.5f
        }?.toInt() ?: 1
    }
    @JvmStatic
    fun changeWindowAlpha(activity: Activity, v: Float) {
        val window = activity.window
        val attributes = window.attributes
        val show = ValueAnimator.ofFloat(attributes.alpha, v)
        show.addUpdateListener { animation ->
            attributes.alpha = animation.animatedValue as Float
            window.attributes = attributes
        }
        show.start()
    }
}