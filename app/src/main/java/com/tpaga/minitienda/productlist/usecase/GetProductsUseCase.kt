package com.tpaga.minitienda.productlist.usecase

import android.content.Context
import com.tpaga.minitienda.UseCase
import com.tpaga.minitienda.data.source.ProductDataSource
import com.tpaga.minitienda.data.source.ProductRepository.Companion.productRepository
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.utils.schedulers.BaseSchedulerProvider
import com.tpaga.minitienda.utils.schedulers.SchedulerProvider
import io.reactivex.Observable

class GetProductsUseCase(schedulerProvider: BaseSchedulerProvider,
                         private val productDataSource: ProductDataSource):UseCase<List<LocalProduct>>(schedulerProvider) {

    override fun createObservable(): Observable<List<LocalProduct>> {
        return productDataSource.getAllProducts()
    }
    companion object {
        fun Context.getProductsUseCase(): GetProductsUseCase {
            return GetProductsUseCase(
                SchedulerProvider.schedulerProvider(),
                productRepository()
            )
        }
    }
}