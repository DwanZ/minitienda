package com.tpaga.minitienda.data.source.remote.api

import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.data.source.remote.entities.SaleRequest
import io.reactivex.Observable

interface APIBuilder {
    fun createSaleRequest(product:LocalProduct):Observable<SaleRequest>

}