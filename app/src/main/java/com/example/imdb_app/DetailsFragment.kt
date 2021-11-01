package com.example.imdb_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imdb_app.data.MovieTop250
import com.example.imdb_app.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var movie: MovieTop250

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie=requireArguments().getParcelable("movie")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init(movie)
    }
    private fun init(movie: MovieTop250) {
        Picasso.get()
            .load(movie.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.MovieDetailsImage)
        binding.MovieDetailsTitle.text = getString(R.string.MovieDetailsTitle, movie.title)
        binding.MovieDetailsDate.text = getString(R.string.MovieDetailsReleaseDate, movie.year)
        binding.MovieDetailsRating.text = getString(R.string.MovieDetailsRating, movie.imDbRating)
    }
}