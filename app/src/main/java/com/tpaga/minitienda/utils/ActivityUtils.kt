package com.tpaga.minitienda.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

object ActivityUtils {

    fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }

    fun replaceFragmentInActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
