package com.example.movieapp.ui.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.application.AppConstants
import com.example.movieapp.databinding.FragmentMovieDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
        Glide.with(requireContext()).load("${AppConstants.IMAGE_URL}${args.posterImageUrl}").centerCrop().into(binding.imgMovie)
        Glide.with(requireContext()).load("${AppConstants.IMAGE_URL}${args.backgroundImageUrl}").centerCrop().into(binding.imgBackground)
        binding.txtDescription.text = args.overview
        binding.textMovieTitle.text = args.title
        binding.textLanguage.text = getString(R.string.language_txt, args.language)
        binding.txtRating.text = getString(R.string.rating_txt, args.voteAverage.toString(), args.voteCount)
        binding.txtRelease.text = getString(R.string.release_txt, args.releaseDate)
    }
}