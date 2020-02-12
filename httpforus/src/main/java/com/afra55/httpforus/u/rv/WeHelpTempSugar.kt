package com.afra55.httpforus.u.rv

import android.content.Context
import android.view.ViewGroup
import com.afra55.httpforus.R
import com.afra55.httpforus.WeHelpLog

/**
 * @author Afra55
 * @date 2020-02-12
 * A smile is the best business card.
 * sugar 示例
 */
class PieTempSugar(context: Context, parent: ViewGroup) : WeHelpBaseSugar(context, parent, R.layout.we_help_sugar_empaty){

    override fun <T : WeHelpBlue> bind(t: T?, clickListener: RecyclerAdapterItemClickListener?) {
        WeHelpLog.i("temp")
    }
}