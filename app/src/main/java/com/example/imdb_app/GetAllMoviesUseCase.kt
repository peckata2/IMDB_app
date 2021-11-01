package com.example.imdb_app

class GetAllMoviesUseCase(private val repo: IMoviesRepository) {

    fun getAllMovies() = repo.getAllMovies()
}