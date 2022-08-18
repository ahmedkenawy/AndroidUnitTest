package com.a7medkenawy.testroom.presentaion.util

sealed class Recourse<T>(val data: T? = null, val message: String? = null) {
    class OnSuccess<T>(data: T) : Recourse<T>(data)
    class OnError<T>(message: String, data: T? = null) : Recourse<T>(data, message)
    class OnLoading<T>() : Recourse<T>()
}
