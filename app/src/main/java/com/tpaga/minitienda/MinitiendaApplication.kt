package com.tpaga.minitienda

import android.annotation.SuppressLint
import android.app.Application
import com.facebook.stetho.Stetho
import com.tpaga.minitienda.data.source.local.room.MinitiendaRoomDatabase.Companion.minitiendaRoomDatabase

class MinitiendaApplication : Application() {

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()
        setupStetho()
        minitiendaRoomDatabase()
    }

    private fun setupStetho() = Stetho.initializeWithDefaults(this)
}
