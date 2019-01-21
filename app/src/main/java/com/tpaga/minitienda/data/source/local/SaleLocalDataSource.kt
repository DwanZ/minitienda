package com.tpaga.minitienda.data.source.local

import android.content.Context
import com.tpaga.minitienda.data.source.SaleDataSource
import com.tpaga.minitienda.data.source.local.room.MinitiendaRoomDatabase
import com.tpaga.minitienda.data.source.local.room.MinitiendaRoomDatabase.Companion.minitiendaRoomDatabase
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale
import com.tpaga.minitienda.data.source.remote.entities.SaleRequest
import io.reactivex.Observable

class SaleLocalDataSource(val minitiendaRoomDatabase: MinitiendaRoomDatabase):SaleDataSource {
    override fun createSaleRequest(saleRequest: SaleRequest): Observable<LocalSale> {
        val s = LocalSale()
        s.statusS = saleRequest.state
        s.dateS = saleRequest.date
        s.totalPrice = saleRequest.totalPrice
        s.token = saleRequest.token
        s.url = saleRequest.url
        s.productId = saleRequest.productId
        minitiendaRoomDatabase.localSale().insertSale(s)
        return findByToken(saleRequest)
    }

    override fun findByToken(saleRequest: SaleRequest): Observable<LocalSale> {
       return minitiendaRoomDatabase.localSale().findByToken(saleRequest.token).toObservable()
    }

    override fun createSaleRequest(product: LocalProduct):Observable<SaleRequest> {
        return Observable.empty()
    }

    override fun getAll(): Observable<List<LocalSale>> {
        return minitiendaRoomDatabase.localSale().getAll().toObservable()
    }

    companion object {
        private var INSTANCE: SaleLocalDataSource? = null
        fun Context.saleLocalDataSource():SaleLocalDataSource{
            if (INSTANCE == null) {
                INSTANCE = SaleLocalDataSource(minitiendaRoomDatabase())
            }
            return INSTANCE!!
        }
    }
}