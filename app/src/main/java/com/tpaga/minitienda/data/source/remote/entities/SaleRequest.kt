package com.tpaga.minitienda.data.source.remote.entities

class SaleRequest(val token:String,
                  val url:String,
                  val state:String,
                  val totalPrice:Double,
                  val date:String,
                  val productId:Int) {
}