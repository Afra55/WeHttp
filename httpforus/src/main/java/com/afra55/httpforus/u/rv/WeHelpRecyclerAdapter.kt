package com.afra55.httpforus.u.rv

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Afra55
 * @date 2019-07-18
 * A smile is the best business card.
 */

abstract class WeHelpRecyclerAdapter(val context:Context) : RecyclerView.Adapter<WeHelpViewHolder>(){


    private val dataList:MutableList<WeHelpBlue> = ArrayList()
    private var itemClickListener: RecyclerAdapterItemClickListener? = null

    fun setOnClickListener(listener: RecyclerAdapterItemClickListener) {
        itemClickListener = listener
    }

    fun initData(tList:List<WeHelpBlue>) {
        dataList.clear()
        dataList.addAll(tList)
    }

    fun clear() {
        dataList.clear()
    }

    fun addAll(tList: List<WeHelpBlue>) {
        dataList.addAll(tList)
    }

    fun add(t: WeHelpBlue) {
        dataList.add(t)
    }

    fun add(position: Int, t: WeHelpBlue) {
        dataList.add(position, t)
    }

    fun indexOf(t:WeHelpBlue): Int {
        return dataList.indexOf(t)
    }

    fun remove(t:WeHelpBlue) {
        if (dataList.contains(t)) {
            dataList.remove(t)
        }
    }

    fun remove(position: Int) {
        if (position < dataList.size && position>=0) {
            dataList.removeAt(position)
        }
    }

    fun contains(t: WeHelpBlue):Boolean {
        return dataList.contains(t)
    }

    fun getDataList() :MutableList<WeHelpBlue>{
        return dataList
    }

    fun getItemData(position: Int) : WeHelpBlue?{
        if (position < dataList.size && position>=0) {
            return dataList[position]
        }
        return null
    }

    fun replaceItemByPos(position: Int, t:WeHelpBlue) {
        if (position < dataList.size && position>=0) {
            dataList.removeAt(position)
            dataList.add(position, t)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeHelpViewHolder {
        return createViewHolder(context, parent, viewType)
    }

    abstract fun createViewHolder(context: Context, parent: ViewGroup, viewType: Int): WeHelpViewHolder


    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position < dataList.size && position >= 0) {
            return dataList[position].viewType
        }
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: WeHelpViewHolder, position: Int) {
        if (holder.getTag() != null) {
            val tag = holder.getTag()!!
            tag.rootView.setOnClickListener {

                itemClickListener?.onItemClicked(holder.adapterPosition, getItemData(holder.adapterPosition))
            }
            tag.rootView.setOnLongClickListener {
                itemClickListener?.onItemLongClicked(holder.adapterPosition, getItemData(holder.adapterPosition)) ?: false
            }
            tag.bind(getItemData(holder.adapterPosition), itemClickListener)
        }
    }
}
interface RecyclerAdapterItemClickListener{

    fun onItemClicked(position: Int, t:WeHelpBlue?, flag:Int = 0)

    fun onItemLongClicked(position: Int, t:WeHelpBlue?, flag: Int = 0):Boolean{
        return false
    }
}
