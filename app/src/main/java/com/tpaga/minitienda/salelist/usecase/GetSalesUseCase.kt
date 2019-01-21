package com.tpaga.minitienda.salelist.usecase

import android.content.Context
import com.tpaga.minitienda.UseCase
import com.tpaga.minitienda.data.source.SaleDataSource
import com.tpaga.minitienda.data.source.SaleRepository.Companion.saleRepository
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale
import com.tpaga.minitienda.utils.schedulers.BaseSchedulerProvider
import com.tpaga.minitienda.utils.schedulers.SchedulerProvider
import io.reactivex.Observable

class GetSalesUseCase(schedulerProvider: BaseSchedulerProvider,
                      private val saleDataSource: SaleDataSource): UseCase<List<LocalSale>>(schedulerProvider) {

    override fun createObservable(): Observable<List<LocalSale>> {
        return saleDataSource.getAll()
    }
    companion object {
        fun Context.getSales(): GetSalesUseCase {
            return GetSalesUseCase(
                SchedulerProvider.schedulerProvider(),
                saleRepository()
            )
        }
    }
}