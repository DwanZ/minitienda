package com.tpaga.minitienda.data.source.remote

import android.content.Context
import com.tpaga.minitienda.data.source.SaleDataSource
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale
import com.tpaga.minitienda.data.source.remote.api.MinitiendaAPI
import com.tpaga.minitienda.data.source.remote.api.MinitiendaAPI.Companion.minitiendaAPI
import com.tpaga.minitienda.data.source.remote.entities.SaleRequest
import io.reactivex.Observable

class SaleRemoteDataSource(val minitiendaAPI: MinitiendaAPI):SaleDataSource {
    override fun createSaleRequest(saleRequest: SaleRequest): Observable<LocalSale> {
        return Observable.empty()
    }

    override fun findByToken(saleRequest: SaleRequest): Observable<LocalSale> {
        return Observable.empty()
    }

    override fun createSaleRequest(product:LocalProduct):Observable<SaleRequest> {
        return minitiendaAPI.createSaleRequest(product)
    }

    override fun getAll(): Observable<List<LocalSale>> {
        return Observable.empty()
    }

    companion object {
        private var INSTANCE: SaleRemoteDataSource? = null
        fun Context.saleRemoteDataSource():SaleRemoteDataSource{
            if (INSTANCE == null) {
                INSTANCE = SaleRemoteDataSource(minitiendaAPI())
            }
            return INSTANCE!!
        }
    }
}