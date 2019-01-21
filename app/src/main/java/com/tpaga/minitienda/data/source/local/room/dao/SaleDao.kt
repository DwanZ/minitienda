package com.tpaga.minitienda.data.source.local.room.dao

import android.arch.persistence.room.*
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface SaleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSales(sales: List<LocalSale>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSale(sale: LocalSale)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSale(status:LocalSale) : Int

    @Delete
    fun remove(localSale: LocalSale)

    @Query("SELECT * FROM sale")
    fun getAll(): Single<List<LocalSale>>

    @Query("SELECT * FROM sale WHERE tokenS LIKE :token")
    fun findByToken(token:String):Single<LocalSale>
}