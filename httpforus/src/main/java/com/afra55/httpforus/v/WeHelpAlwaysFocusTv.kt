package com.afra55.httpforus.v

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * @author Afra55
 * @date 2019-07-23
 * A smile is the best business card.
android:ellipsize="marquee"
android:focusable="true"
android:focusableInTouchMode="true"
android:marqueeRepeatLimit="marquee_forever"
android:singleLine="true"
 */
class WeHelpAlwaysFocusTv : AppCompatTextView{

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun isFocused(): Boolean {
        return true
    }


}