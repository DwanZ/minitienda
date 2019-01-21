package com.tpaga.minitienda.data.source

import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import io.reactivex.Observable

interface ProductDataSource {

    fun createProduct(nameP:String,descriptionP:String,price:Double,statusP:String):Observable<LocalProduct>

    fun getAllProducts():Observable<List<LocalProduct>>

}