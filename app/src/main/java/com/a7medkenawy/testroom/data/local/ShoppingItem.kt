package com.a7medkenawy.testroom.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingItem_table")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var price: Float,
    var amount: Int,
    var imageUrl: String
)