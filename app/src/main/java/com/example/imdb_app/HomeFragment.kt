package com.example.imdb_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.imdb_app.data.MoviesPriority
import com.example.imdb_app.databinding.FragmentCollectionDemoBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    val moviesViewModel:MoviesViewModel by activityViewModels()
    private lateinit var binding: FragmentCollectionDemoBinding
    private lateinit var tabFragmentAdapter: TabFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        tabFragmentAdapter = TabFragmentAdapter(this)
        binding.pager.adapter = tabFragmentAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = MoviesPriority.ALL.priorityRange()
                1 -> tab.text = MoviesPriority.LOW.priorityRange()
                2 -> tab.text = MoviesPriority.MEDIUM.priorityRange()
                3 -> tab.text = MoviesPriority.HIGH.priorityRange()
            }
        }.attach()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        moviesViewModel.getMovies()
    }
}