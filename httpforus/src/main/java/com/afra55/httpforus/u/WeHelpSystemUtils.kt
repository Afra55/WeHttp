package com.afra55.httpforus.u

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.text.Html

/**
 * @author Afra55
 * @date 2019-11-17
 * A smile is the best business card.
 */
object WeHelpSystemUtils {

    @JvmStatic
    fun copyToClipManager(context: Context, label:CharSequence,  text:CharSequence):Boolean {
        return try {//获取剪贴板管理器：
            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            // 创建普通字符型
            val mClipData : ClipData = ClipData.newPlainText(label, text)
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData)
            true
        } catch (e: Exception) {
            false
        }
    }

    @JvmStatic
    fun startEmail(
        ctx: Context, emailSubject: String,
        emailBody: String? = "  ", emailTo: String?
    ) {
        val email = Intent(Intent.ACTION_SEND)
        if (emailBody != null && emailBody != "") {
            email.type = "text/html"
        } else {
            email.type = "application/octet-stream"
        }
        if (emailTo != null && emailTo != "") {
            val emailReciver = arrayOf(emailTo)
            // set default email address
            email.putExtra(Intent.EXTRA_EMAIL, emailReciver)
        }
        // set default mail subject
        email.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
        // set default mail body
        email.putExtra(
            Intent.EXTRA_TEXT,
            Html.fromHtml(emailBody)
        )
        // start send email
        ctx.startActivity(
            Intent.createChooser(
                email,
                "Please choose you client to send!"
            ).addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
            )
        )
    }
}