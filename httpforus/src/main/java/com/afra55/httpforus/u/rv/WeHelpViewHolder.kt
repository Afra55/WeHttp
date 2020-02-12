package com.afra55.httpforus.u.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.afra55.httpforus.R

/**
 * @author Afra55
 * @date 2019-07-18
 * A smile is the best business card.
 */
class WeHelpViewHolder(private val view:View) : RecyclerView.ViewHolder(view){

   companion object{
       @JvmStatic
       fun create(context:Context, parent: ViewGroup,@LayoutRes layoutId: Int): WeHelpViewHolder {
           return WeHelpViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false))
       }
   }

    fun getItemView(): View {
        return view
    }

    fun <T:WeHelpBlue> bind(t:T) {

    }

    fun setTag(sugar: WeHelpBaseSugar) {
        view.setTag(R.integer.we_help_sugar_, sugar)
    }

    fun getTag(): WeHelpBaseSugar?{
        return view.getTag(R.integer.we_help_sugar_) as? WeHelpBaseSugar
    }
}