package com.afra55.httpforus.marker

import android.content.Context
import android.content.DialogInterface
import android.view.Window
import com.afra55.httpforus.WeHelpLog
import java.util.concurrent.ConcurrentHashMap

/**
 * @author Afra55
 * @date 2019-06-24
 * A smile is the best business card.
 */
object PieMaker {
    private var makerMap: ConcurrentHashMap<String, EasyProgressDialog> = ConcurrentHashMap(2)

    fun showMarker(
        context: Context,
        flag: String = "default",
        message: String? = null,
        canCancelable: Boolean = false,
        cancelListener: DialogInterface.OnCancelListener? = null
    ): EasyProgressDialog? {

        try {
            var easyProgressDialog = makerMap[flag]
            @Suppress("CascadeIf")
            if (easyProgressDialog == null) {
                easyProgressDialog = EasyProgressDialog(context, message)
            } else if (easyProgressDialog.context != context) {
                easyProgressDialog.dismiss()
                easyProgressDialog = EasyProgressDialog(context, message)
            } else if (easyProgressDialog.isShowing) {
                return easyProgressDialog
            }

            easyProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            easyProgressDialog.setCancelable(canCancelable)
            easyProgressDialog.setOnCancelListener(cancelListener)
            easyProgressDialog.show()
            makerMap[flag] = easyProgressDialog
            return easyProgressDialog
        } catch (e: Exception) {
            return null
        }
    }

    fun dismissMarker(flag: String? = "default") {
        try {
            var tFlag = flag
            if (tFlag.isNullOrEmpty()) {
                tFlag = "default"
            }
            val easyProgressDialog = makerMap[tFlag]
            easyProgressDialog?.let {
                it.dismiss()
                makerMap.remove(tFlag)
            }
        } catch (e: Exception) {
            WeHelpLog.e("dismissmarker", e)
        }
    }

    fun isShowing(flag: String = "default"): Boolean {
        try {
            val easyProgressDialog = makerMap[flag]
            return easyProgressDialog?.isShowing ?: false
        } catch (e: Exception) {
            return false
        }
    }

    fun setMessage(message: String?, flag: String = "default") {
        try {
            val easyProgressDialog = makerMap[flag]
            easyProgressDialog?.let {
                it.setMessage(message)
            }
        } catch (e: Exception) {
        }
    }

    fun updateMarkerMessage(message: String?, flag: String = "default") {
        try {
            val easyProgressDialog = makerMap[flag]
            easyProgressDialog?.let {
                it.updateLoadingMessage(message)
            }
        } catch (e: Exception) {
        }
    }

}