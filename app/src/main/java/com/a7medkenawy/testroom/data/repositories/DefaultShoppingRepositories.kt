package com.a7medkenawy.testroom.data.repositories

import androidx.lifecycle.LiveData
import com.a7medkenawy.testroom.data.local.ShoppingDao
import com.a7medkenawy.testroom.data.local.ShoppingItem
import com.a7medkenawy.testroom.data.remote.PixapayAPI
import com.a7medkenawy.testroom.data.remote.models.ImageResponse
import com.a7medkenawy.testroom.presentaion.util.Recourse
import retrofit2.Response
import javax.inject.Inject

class DefaultShoppingRepositories @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixapayAPI: PixapayAPI,
) : ShoppingRepositories {


    override fun getImage(searchQuery: String): Recourse<ImageResponse> {
        val response = pixapayAPI.getImages(searchQuery)
        return if (response.isSuccessful) {
            response.body()?.let { Recourse.OnSuccess(it) }
                ?: Recourse.OnError("An UnKnown Error occurred")
        } else {
            Recourse.OnError("couldn't reach the server. check your internet connection")
        }
    }

    override fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun getAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.getAllShoppingItems()
    }

    override fun getAllShoppingItemsPrice(): LiveData<Float> {
        return shoppingDao.getAllShoppingItemsPrice()
    }
}