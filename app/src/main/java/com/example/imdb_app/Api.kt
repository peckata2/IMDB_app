package com.example.imdb_app

import com.example.imdb_app.data.MoviesTop250data
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET("en/API/Top250Movies/k_o0wh622j")
    fun getTop250mMovies(): Single<MoviesTop250data>
}