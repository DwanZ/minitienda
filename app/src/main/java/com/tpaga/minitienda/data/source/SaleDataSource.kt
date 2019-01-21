package com.tpaga.minitienda.data.source

import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale
import com.tpaga.minitienda.data.source.remote.entities.SaleRequest
import io.reactivex.Observable

interface SaleDataSource {

    fun createSaleRequest(product:LocalProduct):Observable<SaleRequest>

    fun createSaleRequest(saleRequest: SaleRequest):Observable<LocalSale>

    fun getAll():Observable<List<LocalSale>>

    fun findByToken(saleRequest:SaleRequest):Observable<LocalSale>
}