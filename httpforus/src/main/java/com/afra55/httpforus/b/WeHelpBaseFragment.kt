package com.afra55.httpforus.b

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.afra55.httpforus.eventbus.WeHelpEventBus
import com.afra55.httpforus.eventbus.WeHelpMessageEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author Afra55
 * @date 2019-07-17
 * A smile is the best business card.
 */
abstract class WeHelpBaseFragment(@IdRes val containerId: Int = 0) : Fragment() {

    var rootView: View? = null
    var isDestroyed = false
    var isPrepared = false
    private var isLazyLoaded = false
    var mActivity: WeHelpBaseActivity? = null
    var isFirst = true

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun fetchEventData(weMessage: WeHelpMessageEvent) {
        onWeHelpMessageFetch(weMessage)
    }

    abstract fun layoutResId(): Int

    abstract fun initLogic()

    abstract fun onWeHelpMessageFetch(weMessage: WeHelpMessageEvent)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(layoutResId(), container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isDestroyed = false
        isPrepared = true
        lazyLoad()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        lazyLoad()
    }

    private fun lazyLoad() {
        if (userVisibleHint && isPrepared && !isLazyLoaded) {
            WeHelpEventBus.register(this)
            initLogic()
            isLazyLoaded = true
        }

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is WeHelpBaseActivity) {
            mActivity = context
        } else {
            throw RuntimeException("$context is a wrong activity")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mActivity = null
    }

    override fun onDestroy() {
        isDestroyed = true
        WeHelpEventBus.unRegister(this)
        super.onDestroy()
    }

    fun setFragmentSelected(selected:Boolean) {
        if (!selected) {
            onFragmentUnSelected()
        } else if (isFirst) {
            onFragmentSelected(true)
            isFirst = false
        } else {
            onFragmentSelected(false)
        }
    }

    abstract fun onFragmentSelected(isFirst:Boolean)
    abstract fun onFragmentUnSelected()

}