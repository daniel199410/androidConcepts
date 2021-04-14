package com.example.movieapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.FragmentMovieBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieFragment : Fragment(R.layout.fragment_movie) {
    private lateinit var binding: FragmentMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
    }
}