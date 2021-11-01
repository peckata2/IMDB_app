package com.example.imdb_app.data

sealed class Result{
    data class Success<T> (val data: T) : Result()
    data class Failure(val msg: String) : Result()
}
