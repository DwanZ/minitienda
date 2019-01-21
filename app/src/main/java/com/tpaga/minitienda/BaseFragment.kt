package com.tpaga.minitienda


import android.arch.lifecycle.LifecycleOwner
import android.support.v4.app.Fragment


open class BaseFragment<T : BaseContract.Presenter> : Fragment(),LifecycleOwner {


    protected lateinit var mPresenter: T

    fun setPresenter(presenter: T) {
        mPresenter = presenter
        lifecycle.addObserver(mPresenter)
    }
}