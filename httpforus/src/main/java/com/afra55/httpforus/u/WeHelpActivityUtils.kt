/*
 * Copyright (c) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.afra55.httpforus.u

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.afra55.httpforus.WeHelpLog
import com.afra55.httpforus.b.WeHelpBaseFragment


/**
 * Utility methods for activities management.
 */
object WeHelpActivityUtils {


    /**
     * Enables back navigation for activities that are launched from the NavBar. See `AndroidManifest.xml` to find out the parent activity names for each activity.
     *
     *
     * `android:parentActivityName=".ui.activity.MainActivity"`
     */
    @JvmStatic
    fun createBackStack(activity: Activity, intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val builder = TaskStackBuilder.create(activity)
            builder.addNextIntentWithParentStack(intent)
            builder.startActivities()
        } else {
            activity.startActivity(intent)
            activity.finish()
        }
    }


    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    @JvmStatic
    fun addFragmentToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int
    ) {
        val addedFragment = fragmentManager.findFragmentById(frameId)
        if (addedFragment == null) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }
    }

    @JvmStatic
    fun replaceFragmentContent(
        fragmentManager: FragmentManager,
        fragment: WeHelpBaseFragment,
        needAddToBackStack: Boolean = false
    ): WeHelpBaseFragment {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(fragment.containerId, fragment)
        if (needAddToBackStack) {
            fragmentTransaction.addToBackStack(fragment::class.simpleName)
        }
        try {
            fragmentTransaction.commitAllowingStateLoss()
        } catch (e: Exception) {
            WeHelpLog.e("replaceFragmentContent", e)
        }

        return fragment
    }

    /**
     * 如果使用 fragment 切换动画或常驻界面的话，最好使用 hide 和 show。
     *
     * @param from
     * @param to
     */
    @JvmStatic
    fun switchFragment(fragmentManager: FragmentManager, from: WeHelpBaseFragment?, to: WeHelpBaseFragment) {
        if (from === to) {
            return
        }
        try {
            val transaction = fragmentManager.beginTransaction()
            if (from == null || !from.isAdded) {
                if (!to.isAdded) {
                    transaction.add(to.containerId, to, to::class.simpleName).commitAllowingStateLoss()
                } else {
                    transaction.show(to).commitAllowingStateLoss()
                }
                to.setFragmentSelected(true)
            } else {
                if (!to.isAdded) {
                    from.setFragmentSelected(false)
                    transaction.hide(from).add(to.containerId, to, to::class.simpleName).commitAllowingStateLoss()
                } else {
                    from.setFragmentSelected(false)
                    transaction.hide(from).show(to).commitAllowingStateLoss()
                    to.setFragmentSelected(true)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
