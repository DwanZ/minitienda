package com.tpaga.minitienda.salelist

import com.tpaga.minitienda.BaseContract
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale

class SaleListContract {
    interface View:BaseContract.View<Presenter>{
        fun showLoading()
        fun hideLoading()
        fun showErrorMessage(error:String)
        fun showProductList(pl:List<LocalSale>)
        fun showSalesNotFound()
    }
    interface Presenter:BaseContract.Presenter{

    }
}