package com.tpaga.minitienda.data.source

import android.content.Context
import com.tpaga.minitienda.data.source.local.SaleLocalDataSource
import com.tpaga.minitienda.data.source.local.SaleLocalDataSource.Companion.saleLocalDataSource
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale
import com.tpaga.minitienda.data.source.remote.SaleRemoteDataSource
import com.tpaga.minitienda.data.source.remote.SaleRemoteDataSource.Companion.saleRemoteDataSource
import com.tpaga.minitienda.data.source.remote.entities.SaleRequest
import io.reactivex.Observable

class SaleRepository(val saleRemoteDataSource: SaleRemoteDataSource,
                     val saleLocalDataSource: SaleLocalDataSource):SaleDataSource {

    override fun createSaleRequest(saleRequest: SaleRequest): Observable<LocalSale> {
        return saleLocalDataSource.createSaleRequest(saleRequest)
    }

    override fun findByToken(saleRequest: SaleRequest): Observable<LocalSale> {
        return saleLocalDataSource.findByToken(saleRequest)
    }

    override fun createSaleRequest(product: LocalProduct):Observable<SaleRequest> {
        return saleRemoteDataSource.createSaleRequest(product)
    }

    override fun getAll(): Observable<List<LocalSale>> {
        return saleLocalDataSource.getAll()
    }

    companion object {
        private var INTANCE: SaleRepository? = null
        fun Context.saleRepository(): SaleRepository {
            if (INTANCE == null) {
                INTANCE = SaleRepository(saleRemoteDataSource(),saleLocalDataSource())
            }
            return INTANCE!!
        }
    }


}