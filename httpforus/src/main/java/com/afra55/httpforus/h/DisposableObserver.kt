package com.afra55.httpforus.h

import android.text.TextUtils
import com.afra55.httpforus.WeHelpLog
import com.afra55.httpforus.u.WeHelpNetWorkUtils
import io.reactivex.observers.DisposableObserver
import java.io.BufferedReader

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 * 这只是个例子，具体自行修改
 * -1 UnKnow 错误； -2 网络错误
 */
abstract class DisposableObserver<T> : DisposableObserver<T>() {

    abstract fun onSuccess(t: T)

    abstract fun onFailure(errorCode: Int, errorMsg: String)

    override fun onNext(t: T) {
        try {
            onSuccess(t)
        } catch (e: Exception) {
            WeHelpLog.e("onNext", e)
            onFailure(-1, "")
        }
    }


    override fun onError(e: Throwable) {
        // 获取根源异常
        var rootThrowable:Throwable = e
        var nextThrowable = e
        while (nextThrowable.cause != null) {
            rootThrowable = nextThrowable
            nextThrowable = nextThrowable.cause!!
        }

        if (WeHelpNetWorkUtils.isOnline()) {
            onFailure(-2, rootThrowable.message?: "Net connection issue")
            return
        }

        if (rootThrowable is retrofit2.HttpException) {
            val code = rootThrowable.code()

            try {
                val errorBody = rootThrowable.response()?.errorBody()
                if (errorBody != null) {
                    val br = BufferedReader(errorBody.charStream())
                    val stringBuilder = StringBuilder()
                    var line:String? = br.readLine()
                    while (!TextUtils.isEmpty(line)) {
                        stringBuilder.append(line)
                        line = br.readLine()
                    }
                    val result = stringBuilder.toString()
                    onFailure(code, result)
                }
            } catch (e: Exception) {
                WeHelpLog.e("body error", e)
            }
            onFailure(code, rootThrowable.message())
            return
        }

        onFailure(-1, rootThrowable.message?:"UnKnow")

    }

    override fun onComplete() {

    }

}