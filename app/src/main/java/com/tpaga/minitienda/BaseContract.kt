package com.tpaga.minitienda

import android.arch.lifecycle.LifecycleObserver

interface BaseContract {

    interface View<in T : Presenter> {

        fun setPresenter(presenter: T)
    }

    interface Presenter: LifecycleObserver {

        fun subscribe()

        fun unsubscribe()
    }
}
