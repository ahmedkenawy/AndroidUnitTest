package com.a7medkenawy.testroom.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.a7medkenawy.testroom.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    private lateinit var shoppingDatabase: ShoppingDatabase
    private lateinit var dao: ShoppingDao

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        shoppingDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = shoppingDatabase.shoppingItemDao()
    }

    @After
    fun tearDown() {
        shoppingDatabase.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testInsertItem() = runTest {
        val shoppingItem = ShoppingItem(0, "Banana", 23f, 2, "")
        dao.insertShoppingItem(shoppingItem)

        val allShoppingItems = dao.getAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDeleteItem() = runTest {
        val shoppingItem = ShoppingItem(0, "Banana", 23f, 2, "")
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)

        val allShoppingItems = dao.getAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testTotalPrice() = runTest {
        val shoppingItem1 = ShoppingItem(0, "Banana", 23f, 2, "url")
        val shoppingItem2 = ShoppingItem(1, "Banana", 5f, 3, "url")
        val shoppingItem3 = ShoppingItem(2, "Banana", 2f, 4, "url")
        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)

        val totalPrice = dao.getAllShoppingItemsPrice().getOrAwaitValue()

        assertThat(totalPrice).isEqualTo(23f * 2 + 5f * 3 + 2f * 4)
    }
}