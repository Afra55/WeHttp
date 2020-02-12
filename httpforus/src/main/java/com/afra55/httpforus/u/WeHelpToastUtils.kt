package com.afra55.httpforus.u

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.StringRes
import com.afra55.httpforus.WeHelp

/**
 * @author Afra55
 * @date 2019-06-20
 * A smile is the best business card.
 */
object WeHelpToastUtils {
    private var toast:Toast? = null

    @SuppressLint("ShowToast")
    fun showToast(message:String?) {
        try {
            if (message.isNullOrEmpty()) {
                return
            }
            val context = WeHelp.context ?: return
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            } else {
                toast!!.setText(message)
            }
            toast?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun showToast(@StringRes resIdRes: Int) {
        try {
            val context = WeHelp.context ?: return
            val message = context.getString(resIdRes)
            showToast(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun <T> showToast(t:T) {
        try {
            showToast(t.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}