package com.example.imdb_app

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.imdb_app.data.MoviesPriority

class TabFragmentAdapter(private val fragment: HomeFragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): TabFragment {
        // Return a NEW fragment instance in createFragment(int)
        val priority = when(position){
            0 -> MoviesPriority.ALL
            1 -> MoviesPriority.LOW
            2 -> MoviesPriority.MEDIUM
            3 -> MoviesPriority.HIGH
            else -> MoviesPriority.ALL
        }
        return TabFragment.newInstance(priority)
    }
}