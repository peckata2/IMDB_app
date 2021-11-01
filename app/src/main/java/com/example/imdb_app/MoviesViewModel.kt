package com.example.imdb_app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb_app.data.MovieTop250
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    private val repo: Repository = Repository()
    private val _moviesLiveData = MutableLiveData<List<MovieTop250>>()
    val moviesLiveData: MutableLiveData<List<MovieTop250>> = _moviesLiveData

    fun getMovies() {
        disposables.add(repo.getAllMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value ->
                    _moviesLiveData.postValue(value)
                    Log.d("Value", "$value")
                },
                { error -> Log.d("Error ", "$error") }

            ))
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
//    fun getMovies(): Observable<List<MovieTop250>> =
//        RetrofitClient.create("https://imdb-api.com/en/API").getTop250mMovies()
//            .map{it.items}.toObservable()

//    fun getMovies() {
//        RetrofitClient.instance.getTop250mMovies().enqueue(object : Callback<MoviesTop250data> {
//            override fun onResponse(
//                call: Call<MoviesTop250data>,
//                response: Response<MoviesTop250data>
//            ) {
//                _moviesLiveData.postValue(Success(response.body()))
//            }
//
//            override fun onFailure(call: Call<MoviesTop250data>, t: Throwable) {
//                Log.d("onFailure ", "${t.message}")
//                _moviesLiveData.postValue(Result.Failure(t.message!!))
//            }
//        })
//        return
//    }
}