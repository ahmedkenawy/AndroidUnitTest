package com.a7medkenawy.testroom.local.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase :RoomDatabase(){
    abstract fun shoppingItemDao(): ShoppingDao
}