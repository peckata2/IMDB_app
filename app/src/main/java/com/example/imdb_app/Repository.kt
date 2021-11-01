package com.example.imdb_app

import com.example.imdb_app.data.MovieTop250
import io.reactivex.Single

class Repository {
    fun getAllMovies(): Single<List<MovieTop250>> {
        return RetrofitClient.create("https://imdb-api.com/").getTop250mMovies()
            .flatMap {
                Single.create { emitter ->
                    if (it.errorMessage.isEmpty()) {
                        emitter.onSuccess(it.items)
                    } else {
                        emitter.onError(Throwable(it.errorMessage))
                    }
                }
            }
    }
}
