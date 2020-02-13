package com.afra55.httpforus.eventbus

/**
 * @author Afra55
 * @date 2019-06-20
 * A smile is the best business card.
 */
class WeHelpMessageEvent(var type:Int, var listAny:ArrayList<Any>? ){

    fun getDefaultObj(): Any? {
        if (listAny == null || listAny!!.isEmpty()) {
            return null
        }
        return listAny!![0]
    }

}