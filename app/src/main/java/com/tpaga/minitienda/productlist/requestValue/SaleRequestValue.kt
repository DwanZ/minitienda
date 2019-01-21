package com.tpaga.minitienda.productlist.requestValue

import com.tpaga.minitienda.UseCase

class SaleRequestValue(val totalPrice:Double,productId:Int,statusS:String,dateS:String):UseCase.RequestValue {
}