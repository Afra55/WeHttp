package com.afra55.httpforus.b

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.afra55.httpforus.eventbus.WeHelpEventBus
import com.afra55.httpforus.eventbus.WeHelpMessageEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author Afra55
 * @date 2019-06-25
 * A smile is the best business card.
 */
open abstract class WeHelpBaseActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)
        initLogic(savedInstanceState)
        WeHelpEventBus.register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun fetchEventData(weMessage: WeHelpMessageEvent) {
        onWeHelpMessageFetch(weMessage)
    }

    abstract fun initLogic(savedInstanceState: Bundle?)

    abstract fun onWeHelpMessageFetch(weMessage: WeHelpMessageEvent)


    override fun onDestroy() {
        WeHelpEventBus.unRegister(this)
        super.onDestroy()
    }

    fun getContext(): Context {
        return this
    }

    fun getActivity(): Activity{
        return this
    }

    fun getAppCompatActivity(): AppCompatActivity {
        return this
    }

    fun setStatusBarColor(@ColorInt color:Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }

    fun setStatusBarFontColorDark() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}