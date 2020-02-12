package com.afra55.httpforus.u.rv

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

/**
 * @author Afra55
 * @date 2019-07-18
 * A smile is the best business card.
 */
abstract class WeHelpBaseSugar(val context: Context, parent:ViewGroup, @LayoutRes layoutId:Int) {

    var holder: WeHelpViewHolder = WeHelpViewHolder.create(context, parent, layoutId)
    var rootView: View

    init {
        rootView = holder.getItemView()
    }

    fun <E:View> findViewById(@IdRes id:Int): E{
        return rootView.findViewById(id)
    }

    abstract fun <T: WeHelpBlue> bind(t:T?, clickListener: RecyclerAdapterItemClickListener?)

    fun getViewHolder(): WeHelpViewHolder {
        if (holder.getTag() != this) {
            holder.setTag(this)
        }
        return holder
    }

    fun getAdapterPosition() :Int{
        return getViewHolder().adapterPosition
    }
}