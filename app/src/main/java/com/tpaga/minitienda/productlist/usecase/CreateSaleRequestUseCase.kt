package com.tpaga.minitienda.productlist.usecase

import android.content.Context
import com.tpaga.minitienda.UseCase
import com.tpaga.minitienda.data.source.SaleDataSource
import com.tpaga.minitienda.data.source.SaleRepository.Companion.saleRepository
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.data.source.remote.entities.SaleRequest
import com.tpaga.minitienda.productlist.requestValue.ProductRequestValue
import com.tpaga.minitienda.utils.schedulers.BaseSchedulerProvider
import com.tpaga.minitienda.utils.schedulers.SchedulerProvider
import io.reactivex.Observable

class CreateSaleRequestUseCase(schedulerProvider: BaseSchedulerProvider,
                               private val saleDataSource: SaleDataSource): UseCase<SaleRequest>(schedulerProvider){
    override fun createObservable(): Observable<SaleRequest> {
        this.mRequestValue
       return saleDataSource.createSaleRequest((this.mRequestValue as ProductRequestValue).product)
    }
    companion object {
        fun Context.createSaleRequestUseCase(): CreateSaleRequestUseCase {
            return CreateSaleRequestUseCase(
                SchedulerProvider.schedulerProvider(),
                saleRepository()
            )
        }
    }
}