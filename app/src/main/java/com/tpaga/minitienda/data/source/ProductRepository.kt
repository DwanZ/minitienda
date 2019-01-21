package com.tpaga.minitienda.data.source

import android.content.Context
import com.tpaga.minitienda.data.source.local.ProductLocalDataSource
import com.tpaga.minitienda.data.source.local.ProductLocalDataSource.Companion.productLocalDataSource
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import io.reactivex.Observable

class ProductRepository(val localDataDource:ProductLocalDataSource):ProductDataSource {

    override fun createProduct(
        nameP: String,
        descriptionP: String,
        price: Double,
        statusP: String
    ): Observable<LocalProduct> {
        return localDataDource.createProduct(nameP, descriptionP,price,statusP)
    }


    override fun getAllProducts():Observable<List<LocalProduct>> {
       return localDataDource.getAllProducts()
    }

    companion object {
        private var INSTANCE: ProductRepository? = null
        fun Context.productRepository():ProductRepository{
            if (INSTANCE == null) {
                INSTANCE = ProductRepository(productLocalDataSource())
            }
            return INSTANCE!!
        }
    }
}

