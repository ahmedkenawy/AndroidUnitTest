package com.a7medkenawy.testroom.data.di

import android.content.Context
import androidx.room.Room
import com.a7medkenawy.testroom.data.local.ShoppingDatabase
import com.a7medkenawy.testroom.presentaion.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShoppingDatabase::class.java, DATABASE_NAME)

    @Singleton
    @Provides
    fun provideShoppingDao(database: ShoppingDatabase) = database.shoppingItemDao()
}