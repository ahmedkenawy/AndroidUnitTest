package com.a7medkenawy.testroom.data.repositories

import androidx.lifecycle.LiveData
import com.a7medkenawy.testroom.data.local.ShoppingItem
import com.a7medkenawy.testroom.data.remote.models.ImageResponse
import com.a7medkenawy.testroom.presentaion.util.Recourse

interface ShoppingRepositories {

    fun getImage(searchQuery: String): Recourse<ImageResponse>

    fun insertShoppingItem(shoppingItem: ShoppingItem)

    fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun getAllShoppingItemsPrice(): LiveData<Float>
}