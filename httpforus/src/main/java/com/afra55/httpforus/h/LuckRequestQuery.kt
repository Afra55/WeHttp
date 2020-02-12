package com.afra55.httpforus.h

/**
 * Created by yangshuai on 2017/8/19.
 * {link http://afra55.github.io}
 */

class LuckRequestQuery {

    var data = HashMap<String, Any>()



    fun withParam(param: String, obj: Any?): LuckRequestQuery {
        if (obj != null) {
            data[param] = obj
        }
        return this
    }

    fun build(): Map<String,Any> {
        return data
    }

}
