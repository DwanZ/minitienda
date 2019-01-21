package com.tpaga.minitienda.data.source.local

import android.content.Context
import com.tpaga.minitienda.data.source.ProductDataSource
import com.tpaga.minitienda.data.source.local.room.MinitiendaRoomDatabase
import com.tpaga.minitienda.data.source.local.room.MinitiendaRoomDatabase.Companion.minitiendaRoomDatabase
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import io.reactivex.Observable

class ProductLocalDataSource(private val minitiendaRoomDatabase: MinitiendaRoomDatabase):ProductDataSource {

    override fun createProduct(nameP: String, descriptionP: String,price: Double,statusP: String): Observable<LocalProduct> {
        val localProduct = LocalProduct()
        localProduct.name = nameP
        localProduct.description=descriptionP
        localProduct.price=price
        localProduct.status=statusP
        minitiendaRoomDatabase.localProduct().insertProduct(localProduct)
        return Observable.just(localProduct)
    }


    override fun getAllProducts():Observable<List<LocalProduct>> {
        return minitiendaRoomDatabase.localProduct().getAll().toObservable()
    }

    companion object {
        private var INSTANCE: ProductLocalDataSource? = null
        fun Context.productLocalDataSource():ProductLocalDataSource{
            if (INSTANCE == null) {
                INSTANCE = ProductLocalDataSource(minitiendaRoomDatabase())
            }
            return INSTANCE!!
        }
    }
}