package com.a7medkenawy.testroom.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.a7medkenawy.testroom.data.local.ShoppingItem
import com.a7medkenawy.testroom.data.remote.models.ImageResponse
import com.a7medkenawy.testroom.data.repositories.ShoppingRepositories
import com.a7medkenawy.testroom.presentaion.util.Recourse

class FakeShoppingRepository : ShoppingRepositories {

    val shoppingItems = mutableListOf<ShoppingItem>()

    val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false
    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float? {
        return shoppingItems.sumByDouble {
            it.price.toDouble()
        }.toFloat()
    }

    override fun getImage(searchQuery: String): Recourse<ImageResponse> {
        return if (shouldReturnNetworkError) {
            Recourse.OnSuccess(ImageResponse(listOf(), 0, 0))
        } else {
            Recourse.OnError("Error")
        }
    }

    override fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun getAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    override fun getAllShoppingItemsPrice(): LiveData<Float> {
        return observableTotalPrice
    }

}