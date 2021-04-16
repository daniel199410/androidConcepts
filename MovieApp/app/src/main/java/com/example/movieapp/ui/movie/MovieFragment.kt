package com.example.movieapp.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.data.local.AppDatabase
import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.remote.RemoteMovieDataSource
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.MovieViewModelFactory
import com.example.movieapp.repository.MovieRepositoryImpl
import com.example.movieapp.repository.RetrofitClient
import com.example.movieapp.ui.movie.adapters.MovieAdapter
import com.example.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.movieapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.movieapp.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var concatAdapter: ConcatAdapter
    private val viewModel by viewModels<MovieViewModel> { MovieViewModelFactory(MovieRepositoryImpl(
        RemoteMovieDataSource(RetrofitClient.webService),
        LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        concatAdapter = ConcatAdapter()
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Loading -> binding.progessBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progessBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpcomingConcatAdapter(MovieAdapter(it.data.first.results, this@MovieFragment)))
                        addAdapter(1, TopRatedConcatAdapter(MovieAdapter(it.data.second.results, this@MovieFragment)))
                        addAdapter(2, PopularConcatAdapter(MovieAdapter(it.data.third.results, this@MovieFragment)))
                    }
                    binding.rdMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progessBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionFirstFragmentToSecondFragment(
            movie.posterPath,
            movie.backdropPath,
            movie.voteAverage.toFloat(),
            movie.voteCount,
            movie.overview,
            movie.title,
            movie.originalLanguage,
            movie.releaseDate
        )
        findNavController().navigate(action)
    }
}