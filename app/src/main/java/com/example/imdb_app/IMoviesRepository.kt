package com.example.imdb_app

import com.example.imdb_app.data.MovieTop250
import io.reactivex.Observable

interface IMoviesRepository {

    fun getAllMovies(): Observable<List<MovieTop250>>
    fun addMovie()
    fun removeMovies(movie: MovieTop250)
}
