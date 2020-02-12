package com.afra55.httpforus.marker

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager.LayoutParams
import android.widget.TextView
import com.afra55.httpforus.R


/**
 * @author Afra55
 * @date 2018/4/4
 * 一个半透明窗口,包含一个Progressbar 和 Message部分. 其中Message部分可选. 可单独使用,也可以使用
 * [DialogMaker] 进行相关窗口显示.
 */
class EasyProgressDialog @JvmOverloads constructor(
    mContext: Context,
    private var mMessage: String? = null,
    style: Int = R.style.easy_dialog_style,
    private val mLayoutId: Int = R.layout.easy_progress_dialog
) : Dialog(mContext, style) {

    private var message: TextView? = null

    init {
        if (window != null) {
            val params = window!!.attributes
            params.width = LayoutParams.MATCH_PARENT
            params.height = LayoutParams.MATCH_PARENT
            window!!.attributes = params
        }
    }

    fun setMessage(msg: String?) {
        mMessage = msg
    }

    fun updateLoadingMessage(msg: String?) {
        mMessage = msg
        showMessage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayoutId)
        message = findViewById<View>(R.id.easy_progress_dialog_message) as TextView
        showMessage()
    }

    private fun showMessage() {
        if (message != null){
            if (!TextUtils.isEmpty(mMessage)) {
                message!!.visibility = View.VISIBLE
                message!!.text = mMessage
            } else {
                message!!.visibility = View.GONE
            }
        }
    }
}