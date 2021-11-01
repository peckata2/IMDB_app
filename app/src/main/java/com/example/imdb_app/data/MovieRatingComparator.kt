package com.example.imdb_app.data

class MovieRatingComparator {
    companion object : Comparator<MovieTop250> {
        override fun compare(o1: MovieTop250, o2: MovieTop250): Int {
            return when {
                o1.imDbRating.toFloat() > o2.imDbRating.toFloat() -> 1
                o1.imDbRating.toFloat() < o2.imDbRating.toFloat() -> -1
                else -> 0
            }
        }

    }
}