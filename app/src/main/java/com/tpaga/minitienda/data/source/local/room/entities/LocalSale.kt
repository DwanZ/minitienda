package com.tpaga.minitienda.data.source.local.room.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "sale")
class LocalSale {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_sale")
    var id: Int = (Math.random() * Int.MAX_VALUE).toInt()

    @ColumnInfo(name = "totalPrice")
    var totalPrice: Double? = null

    @ColumnInfo(name = "productId")
    var productId: Int = 0

    @ColumnInfo(name = "tokenS")
    var token: String? = null

    @ColumnInfo(name = "statusS")
    var statusS: String? = null

    @ColumnInfo(name = "dateS")
    var dateS: String? = null

    @ColumnInfo(name = "url")
    var url: String? = null
}