package com.afra55.httpforus.eventbus

import org.greenrobot.eventbus.EventBus

/**
 * @author Afra55
 * @date 2019-06-20
 * A smile is the best business card.
 */
object WeHelpEventBus {




    @JvmStatic
    fun postError(type: Int, errorCode: Int, errorMessage: String, errorObject: Any? = null) {
        EventBus.getDefault().post(WeHelpMessageEvent(type, arrayListOf(WeHelpError(errorCode, errorMessage, errorObject))))
    }

    @JvmStatic
    fun post(type: Int, vararg any: Any) {
        val event = arrayListOf<Any>()
        for (v in any) {
            event.add(v)
        }
        EventBus.getDefault().post(WeHelpMessageEvent(type, event))
    }

    @JvmStatic
    fun postSticky(type: Int, vararg any:Any) {
        val event = arrayListOf<Any>()
        for (v in any) {
            event.add(v)
        }
        EventBus.getDefault().postSticky(WeHelpMessageEvent(type, event))
    }

    @JvmStatic
    fun removeSticky(messageEvent: WeHelpMessageEvent) {
        EventBus.getDefault().removeStickyEvent(messageEvent)
    }

    @JvmStatic
    fun register(any: Any) {
        EventBus.getDefault().register(any)
    }

    @JvmStatic
    fun unRegister(any: Any) {
        if (EventBus.getDefault().isRegistered(any)) {
            EventBus.getDefault().unregister(any)
        }
    }
}