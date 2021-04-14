package com.example.movieapp.ui.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
    }
}