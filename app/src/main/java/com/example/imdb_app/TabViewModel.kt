package com.example.imdb_app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb_app.data.MovieRatingComparator
import com.example.imdb_app.data.MovieTop250
import com.example.imdb_app.data.MoviesPriority
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TabViewModel : ViewModel() {
    private val repo:Repository=Repository()
    private val _tabLiveData = MutableLiveData<List<MovieTop250>>()
    val tabLiveData: MutableLiveData<List<MovieTop250>> = _tabLiveData

    fun filterMovies(priority:MoviesPriority){
        repo.getAllMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {value-> _tabLiveData.postValue(value)},
                {error -> Log.d("Error ","$error")}
            )
            .dispose()
    }

    fun filterMoviesByPriority(movies: List<MovieTop250>,priority: MoviesPriority) {

        when (priority) {
            MoviesPriority.ALL -> {
                tabLiveData.postValue(movies.sortedWith(MovieRatingComparator))
                Log.d("getMovies ALL ", "$movies")
            }
            MoviesPriority.LOW -> {
                tabLiveData.postValue(movies.filter { it.imDbRating.toFloat() <= 5 }
                    .sortedWith(MovieRatingComparator))
                Log.d("getMovies LOW ", "$movies")
            }
            MoviesPriority.MEDIUM -> {
                tabLiveData.postValue(movies.filter { it.imDbRating.toFloat() > 5 && it.imDbRating.toFloat() <= 8 }
                    .sortedWith(MovieRatingComparator))
                Log.d("getMovies MEDIUM ", "$movies")
            }
            MoviesPriority.HIGH -> {
                tabLiveData.postValue(movies.filter { it.imDbRating.toFloat() > 8 }
                    .sortedWith(MovieRatingComparator))
                Log.d("getMovies HIGH", "$movies")
            }
        }
    }
}