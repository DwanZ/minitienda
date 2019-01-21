package com.tpaga.minitienda.productlist.usecase

import android.content.Context
import com.tpaga.minitienda.UseCase
import com.tpaga.minitienda.data.source.ProductDataSource
import com.tpaga.minitienda.data.source.ProductRepository.Companion.productRepository
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.utils.schedulers.BaseSchedulerProvider
import com.tpaga.minitienda.utils.schedulers.SchedulerProvider
import io.reactivex.Observable

class CreateProductsUseCase(schedulerProvider: BaseSchedulerProvider,
                            private val productDataSource: ProductDataSource): UseCase<List<LocalProduct>>(schedulerProvider) {

    override fun createObservable(): Observable<List<LocalProduct>> {
        return productDataSource.createProduct("Shampoo",
            "Con vitamina E y embrión de pato, contenido del producto 400ml",
            20000.0,"activo")
            .doOnNext {
                productDataSource.createProduct("Acondicionador",
                    "Para idratación profunda, con vitamina E, contenido del producto 210ml",
                    25000.0,"activo")
            }
            .doOnNext {
                productDataSource.createProduct("Crema dental",
                    "Triple acción con sabor a menta forte, presentación de 75ml",
                    5000.0,"activo")
            }
            .doOnNext {
                productDataSource.createProduct("Control inalámbrico",
                    "\"Control inalámbrico con conexión USB compatible con todo ordenador, marca gato, modelo XBOX",
                    40000.0,"activo")
            }.doOnNext {
                productDataSource.createProduct("Atún Calvo",
                    "Conservado en aceite, contiene zanahoria arveja y maicitos, contenido de 320 gr",
                    7000.0,"activo")
            }
            .doOnNext {
                productDataSource.createProduct("Salsa de tomate Fruco",
                    "Salta de tomate con todo el sabor del campo, contenido de 800gr",
                    10000.0,"activo")
            }
            .flatMap { productDataSource.getAllProducts() }
    }
    companion object {
        fun Context.createProducts(): CreateProductsUseCase {
            return CreateProductsUseCase(
                SchedulerProvider.schedulerProvider(),
                productRepository()
            )
        }
    }
}