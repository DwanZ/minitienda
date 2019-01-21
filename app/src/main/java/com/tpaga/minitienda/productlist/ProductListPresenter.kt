package com.tpaga.minitienda.productlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale
import com.tpaga.minitienda.data.source.remote.entities.SaleRequest
import com.tpaga.minitienda.productlist.requestValue.ProductRequestValue
import com.tpaga.minitienda.productlist.requestValue.SRequestValue
import com.tpaga.minitienda.productlist.usecase.*
import com.tpaga.minitienda.utils.exception.ErrorMessageFactory
import io.reactivex.observers.DisposableObserver

class ProductListPresenter(private val mView: ProductListContract.View,
                           private val getProductsUseCase: GetProductsUseCase,
                           private val createProductsUseCase: CreateProductsUseCase,
                           private val createSaleRequestUseCase: CreateSaleRequestUseCase,
                           private val saveLocalSaleUseCase: SaveLocalSaleUseCase,
                           private val errorMessageFactory: ErrorMessageFactory):ProductListContract.Presenter {


    init {
        mView.setPresenter(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun subscribe() {
        mView.showLoading()
        getProductsUseCase.execute(GetProductsObserver())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun unsubscribe() {
        getProductsUseCase.unsubscribe()
        createProductsUseCase.unsubscribe()
        createSaleRequestUseCase.unsubscribe()
        saveLocalSaleUseCase.unsubscribe()
    }

    override fun onBuyCLicked(product: LocalProduct) {
        mView.showSendingData()
        createSaleRequestUseCase.execute(CreateSaleRequestObserver(),ProductRequestValue(product))
    }

    inner class GetProductsObserver:DisposableObserver<List<LocalProduct>>(){
        override fun onComplete() {
            //nothing by context
        }
        override fun onNext(t: List<LocalProduct>) {
            if(t.isEmpty() ){
                createProductsUseCase.execute(CreateProductsObserver())
            }else{
                mView.hideLoading()
                mView.showProductList(t)
            }
        }
        override fun onError(e: Throwable) {
            mView.hideLoading()
            mView.showErrorMessage(errorMessageFactory.create(e))
        }
    }
    inner class CreateProductsObserver:DisposableObserver<List<LocalProduct>>(){
        override fun onComplete() {
            //nothing by context
        }
        override fun onNext(t: List<LocalProduct>) {
            mView.hideLoading()
            mView.showProductList(t)
        }
        override fun onError(e: Throwable) {
            mView.hideLoading()
            mView.showErrorMessage(errorMessageFactory.create(e))
        }
    }
    inner class CreateSaleRequestObserver:DisposableObserver<SaleRequest>(){
        override fun onComplete() {
            //nothing by context
        }
        override fun onNext(t: SaleRequest) {
            //mView.hideSendingData()
            saveLocalSaleUseCase.execute(SaveSaleObserver(),SRequestValue(t))
            //mView.showSuccessMessage(t.url)
        }
        override fun onError(e: Throwable) {
            mView.hideSendingData()
            mView.showErrorMessage(errorMessageFactory.create(e))
        }
    }
    inner class SaveSaleObserver:DisposableObserver<LocalSale>(){
        override fun onComplete() {
            //nothing by context
        }
        override fun onNext(t: LocalSale) {
            mView.hideSendingData()
            mView.showSuccessMessage(t.url!!)
        }
        override fun onError(e: Throwable) {
            mView.hideSendingData()
            mView.showErrorMessage(errorMessageFactory.create(e))
        }
    }

}