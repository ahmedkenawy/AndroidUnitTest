package com.a7medkenawy.testroom.local.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShoppingDao {

    @Insert
    fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("select * from shoppingItem_table")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>

    @Query("select sum(price*amount) from shoppingItem_table")
    fun getAllShoppingItemsPrice(): LiveData<List<Float>>
}