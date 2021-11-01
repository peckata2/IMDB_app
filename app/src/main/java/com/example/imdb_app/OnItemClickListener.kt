package com.example.imdb_app

import com.example.imdb_app.data.MovieTop250

interface OnItemClickListener {
    fun onItemClicked(movie:MovieTop250)
}