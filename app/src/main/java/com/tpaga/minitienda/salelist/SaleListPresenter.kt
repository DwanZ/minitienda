package com.tpaga.minitienda.salelist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale
import com.tpaga.minitienda.salelist.usecase.GetSalesUseCase
import com.tpaga.minitienda.utils.exception.ErrorMessageFactory
import io.reactivex.observers.DisposableObserver

class SaleListPresenter(private val mView: SaleListContract.View,
                        private val getSalesUseCase: GetSalesUseCase,
                        private val errorMessageFactory: ErrorMessageFactory):SaleListContract.Presenter {

    init {
        mView.setPresenter(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun subscribe() {
        getSalesUseCase.execute(GetSalesObserver())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun unsubscribe() {
        getSalesUseCase.unsubscribe()
    }
    inner class GetSalesObserver:DisposableObserver<List<LocalSale>>() {
        override fun onComplete() {
            //nothing by context
        }

        override fun onNext(t: List<LocalSale>) {
            mView.hideLoading()
            if(t.isNotEmpty()){
                mView.showProductList(t)
            }else{
                mView.showSalesNotFound()
            }
        }

        override fun onError(e: Throwable) {
            mView.hideLoading()
            mView.showErrorMessage(errorMessageFactory.create(e))
        }
    }
}