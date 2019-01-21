package com.tpaga.minitienda.productlist.usecase

import android.content.Context
import com.tpaga.minitienda.UseCase
import com.tpaga.minitienda.data.source.SaleDataSource
import com.tpaga.minitienda.data.source.SaleRepository.Companion.saleRepository
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale
import com.tpaga.minitienda.productlist.requestValue.SRequestValue
import com.tpaga.minitienda.utils.schedulers.BaseSchedulerProvider
import com.tpaga.minitienda.utils.schedulers.SchedulerProvider
import io.reactivex.Observable

class SaveLocalSaleUseCase(schedulerProvider: BaseSchedulerProvider,
                           private val saleDataSource: SaleDataSource): UseCase<LocalSale>(schedulerProvider) {
    override fun createObservable(): Observable<LocalSale> {

        return saleDataSource.createSaleRequest((this.mRequestValue as SRequestValue).saleRequest)
    }
    companion object {
        fun Context.saveLocalSaleUseCase(): SaveLocalSaleUseCase {
            return SaveLocalSaleUseCase(
                SchedulerProvider.schedulerProvider(),
                saleRepository()
            )
        }
    }
}