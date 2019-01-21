package com.tpaga.minitienda.data.source.local.room.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "product")
class LocalProduct {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_product")
    var id: Int = (Math.random() * Int.MAX_VALUE).toInt()

    @ColumnInfo(name = "nameP")
    var name: String? = null

    @ColumnInfo(name = "descriptionP")
    var description: String? = null

    @ColumnInfo(name = "price")
    var price: Double? = null

    @ColumnInfo(name = "image")
    var image: String? = null

    @ColumnInfo(name = "statusP")
    var status: String? = null

}