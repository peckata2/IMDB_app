package com.example.imdb_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imdb_app.data.MovieTop250
import com.example.imdb_app.data.MoviesPriority
import com.example.imdb_app.data.MoviesTop250data
import com.example.imdb_app.data.Result
import com.example.imdb_app.databinding.TabFragmentBinding

class TabFragment : Fragment(), OnItemClickListener {
    companion object {
        fun newInstance(moviesPriority: MoviesPriority): TabFragment =
            TabFragment().apply {
                arguments = Bundle(1).apply {
                    putSerializable("priority", moviesPriority)
                }
            }
    }

    lateinit var binding: TabFragmentBinding
    private val moviesViewModel: MoviesViewModel by activityViewModels()
    private lateinit var tabviewModel: TabViewModel

    private lateinit var priority: MoviesPriority

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TabFragmentBinding.inflate(inflater, container, false)
        priority = arguments?.get("priority") as MoviesPriority
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("priority", "$priority")
        tabviewModel = ViewModelProvider(this).get(TabViewModel::class.java)
        binding.MoviesTop250RecyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.MoviesTop250RecyclerView.adapter = MoviesTop250RecyclerViewAdapter(this)

        moviesViewModel.moviesLiveData.observe(viewLifecycleOwner, {
            tabviewModel.filterMoviesByPriority(it, priority)
            setupListeners()
            Log.d("Result","$it")
        })
    }


//        moviesViewModel.moviesLiveData.observe(viewLifecycleOwner, { result ->
//            when (result) {
//                is Result.Success<*> -> {
//                    tabviewModel.filterMoviesByPriority(
//                        (result.data as MoviesTop250data).items, priority
//                    )
//                    setupListeners()
//                }
////                is Result.Failure -> {
////                    Toast.makeText(activity, result.msg, Toast.LENGTH_SHORT).show()
////                }
//            }
//        })
//    }

    private fun setupListeners() {
        tabviewModel.tabLiveData.observe(viewLifecycleOwner, { movies ->
            binding.MoviesTop250RecyclerView.adapter?.let {
                (it as MoviesTop250RecyclerViewAdapter).setMovieList(
                    movies
                )
            }
        })
    }

    override fun onItemClicked(movie: MovieTop250) {
        findNavController().navigate(R.id.detailsFragment, bundleOf("movie" to movie))
    }
}
