package com.tpaga.minitienda.data.source.local.room.dao

import android.arch.persistence.room.*
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProducts(products: List<LocalProduct>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(products: LocalProduct)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateProducts(products: List<LocalProduct>) : Int

    @Delete
    fun remove(localProduct: LocalProduct)

    @Query("SELECT * FROM product WHERE id_product = :productId")
    fun getProductById(productId:Long): Single<LocalProduct>

    @Query("SELECT * FROM product")
    fun getAll(): Single<List<LocalProduct>>
}