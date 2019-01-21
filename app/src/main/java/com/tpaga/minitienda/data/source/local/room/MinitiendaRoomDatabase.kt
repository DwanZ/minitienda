package com.tpaga.minitienda.data.source.local.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.tpaga.minitienda.data.source.local.room.dao.ProductDao
import com.tpaga.minitienda.data.source.local.room.dao.SaleDao
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale


@Database(entities = [LocalProduct::class,LocalSale::class],version = 1,exportSchema = false)
abstract class MinitiendaRoomDatabase : RoomDatabase() {

    abstract fun localProduct(): ProductDao
    abstract fun localSale(): SaleDao

    companion object {

        private const val DB_NAME = "com.tpaga.MinitiendaRoomDatabase"
        private var INSTANCE: MinitiendaRoomDatabase? = null

        fun Context.minitiendaRoomDatabase(): MinitiendaRoomDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        applicationContext,
                        MinitiendaRoomDatabase::class.java,
                        DB_NAME
                )
                .addMigrations()
                .allowMainThreadQueries()
                .build()
            }
            return INSTANCE!!
        }
    }
}