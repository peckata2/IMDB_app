package com.example.imdb_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb_app.data.MovieTop250
import com.example.imdb_app.databinding.MovieLayoutBinding
import com.squareup.picasso.Picasso

class MoviesTop250RecyclerViewAdapter(val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MoviesTop250RecyclerViewAdapter.MoviesTop250ViewHolder>() {
    private var movies = mutableListOf<MovieTop250>()


    fun setMovieList(movies: List<MovieTop250>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesTop250ViewHolder {
        val binding = MovieLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviesTop250ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MoviesTop250ViewHolder, position: Int) {
        holder.bind(movies[position], itemClickListener)
    }

    class MoviesTop250ViewHolder(private val binding: MovieLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieTop250, clickListener: OnItemClickListener) {
            Picasso.get()
                .load(movie.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.MovieImage);
            binding.MovieTitleTextView.text = movie.title
            binding.MovieRatingTextView.text = movie.imDbRating
            binding.MovieYearTextView.text = movie.year

            itemView.setOnClickListener {
                clickListener.onItemClicked(movie)
            }
        }

    }

    override fun getItemCount() = movies.size

}
