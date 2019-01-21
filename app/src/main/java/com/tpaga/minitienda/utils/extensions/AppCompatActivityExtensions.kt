package com.tpaga.minitienda.utils.extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun <T: Fragment> AppCompatActivity.findFragment(id: Int): T? {
    @Suppress("UNCHECKED_CAST")
    return supportFragmentManager.findFragmentById(id) as T?
}

fun<T: Fragment> AppCompatActivity.addFragment(fragment: T, frameId: Int):T {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
    return fragment
}

fun<T: Fragment> AppCompatActivity.replaceFragment(fragment: T, frameId: Int):T {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(frameId, fragment)
    transaction.commit()
    return fragment
}