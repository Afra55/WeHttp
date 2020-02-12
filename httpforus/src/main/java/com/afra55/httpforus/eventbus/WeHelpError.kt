package com.afra55.httpforus.eventbus

/**
 * @author Afra55
 * @date 2019-06-25
 * A smile is the best business card.
 */
data class WeHelpError(val errCode:Int, val errMsg:String, var errorObj:Any? = null)