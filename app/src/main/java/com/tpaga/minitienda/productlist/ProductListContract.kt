package com.tpaga.minitienda.productlist

import com.tpaga.minitienda.BaseContract
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct

class ProductListContract {
    interface View:BaseContract.View<Presenter>{
        fun showLoading()
        fun hideLoading()
        fun showSendingData()
        fun hideSendingData()
        fun showErrorMessage(error:String)
        fun showProductList(pl:List<LocalProduct>)
        fun showSuccessMessage(link:String)
        fun showTpaga(url:String)
    }
    interface Presenter:BaseContract.Presenter{
        fun onBuyCLicked(product:LocalProduct)

    }
}