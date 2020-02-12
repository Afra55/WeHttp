package com.afra55.httpforus

import android.util.Log

object WeHelpLog {
    @JvmStatic
    var PRE_TAG = "FORUS_"
    @JvmStatic
    var isDebug = false

    @JvmStatic
    fun makeLogTag(string: String): String {
        if (string.length > 23 - PRE_TAG.length) {
            return PRE_TAG + string.substring(0, 23 - PRE_TAG.length - 1)
        }
        return PRE_TAG + string
    }

    @JvmStatic
    private fun getStackOffset(trace: Array<StackTraceElement>): Int {
        var i = 2
        while (i < trace.size) {
            val e = trace[i]
            val name = e.className
            if (name != WeHelpLog::class.java.name) {
                return --i
            }
            i++
        }
        return -1
    }

    @JvmStatic
    private fun getSimpleClassName(name: String): String {
        val lastIndex = name.lastIndexOf(".")
        return name.substring(lastIndex + 1)
    }

    @JvmStatic
    private fun getTraceElement(): StackTraceElement {
        val trace = Thread.currentThread().stackTrace
        var stackOffset = getStackOffset(trace) + 1

        if (stackOffset > trace.size) {
            stackOffset = trace.size - 1
        }
        return trace[stackOffset]

    }

    @JvmStatic
    fun getTag(element: StackTraceElement): String {
        return makeLogTag(getSimpleClassName(element.className))
    }

    @JvmStatic
    private fun getMessage(element: StackTraceElement, msg: String): String {
        return getSimpleClassName(element.className) +
                "." +
                element.methodName +
                " " +
                " (" +
                element.fileName +
                ":" +
                element.lineNumber +
                ")" +
                " | " +
                msg
    }

    @JvmStatic
    fun i(message: String, oTag: String? = null) {
        if (!isDebug) {
            return
        }
        val element = getTraceElement()
        val tag = oTag ?: getTag(element)
        Log.i(tag, getMessage(element, message))
    }

    @JvmStatic
    fun i(message: String, throwable: Throwable, oTag: String? = null) {
        if (!isDebug) {
            return
        }
        val element = getTraceElement()
        val tag = oTag ?: getTag(element)
        Log.i(tag, getMessage(element, message), throwable)
    }

    @JvmStatic
    fun w(message: String, oTag: String? = null) {
        if (!isDebug) {
            return
        }
        val element = getTraceElement()
        val tag = oTag ?: getTag(element)
        Log.w(tag, getMessage(element, message))
    }

    @JvmStatic
    fun w(message: String, throwable: Throwable, oTag: String? = null) {
        if (!isDebug) {
            return
        }
        val element = getTraceElement()
        val tag = oTag ?: getTag(element)
        Log.w(tag, getMessage(element, message), throwable)
    }

    @JvmStatic
    fun v(message: String, oTag: String? = null) {
        if (!isDebug) {
            return
        }
        val element = getTraceElement()
        val tag = oTag ?: getTag(element)
        Log.v(tag, getMessage(element, message))
    }

    @JvmStatic
    fun v(message: String, throwable: Throwable, oTag: String? = null) {
        if (!isDebug) {
            return
        }
        val element = getTraceElement()
        val tag = oTag ?: getTag(element)
        Log.v(tag, getMessage(element, message), throwable)
    }

    @JvmStatic
    fun e(message: String, oTag: String? = null) {
        if (!isDebug) {
            return
        }
        val element = getTraceElement()
        val tag = oTag ?: getTag(element)
        Log.e(tag, getMessage(element, message))
    }

    @JvmStatic
    fun e(message: String, throwable: Throwable, oTag: String? = null) {
        if (!isDebug) {
            return
        }
        val element = getTraceElement()
        val tag = oTag ?: getTag(element)
        Log.e(tag, getMessage(element, message), throwable)
    }


    @JvmStatic
    fun d(message: String, oTag: String? = null) {
        if (!isDebug) {
            return
        }
        val element = getTraceElement()
        val tag = oTag ?: getTag(element)
        Log.d(tag, getMessage(element, message))
    }

    @JvmStatic
    fun d(message: String, throwable: Throwable, oTag: String? = null) {
        if (!isDebug) {
            return
        }
        val element = getTraceElement()
        val tag = oTag ?: getTag(element)
        Log.d(tag, getMessage(element, message), throwable)
    }


}