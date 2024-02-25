package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.domain.entities.Movie
import com.example.moviesapp.presentation.adapters.MovieAdapter
import com.example.moviesapp.presentation.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);

        val viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("response", throwable.message!!)
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            viewModel.getPopularMovies()
        }
        viewModel.movies.observe(this) {
            initRecyclerview(it)
            binding.progressbar.visibility = View.INVISIBLE
        }

        handleRefresh(viewModel, coroutineExceptionHandler)
    }

    private fun handleRefresh(viewModel: MoviesViewModel, coroutineExceptionHandler: CoroutineExceptionHandler) {
        binding.refreshLayout.setOnRefreshListener {
            var handler = Handler()
            handler.postDelayed({
                binding.refreshLayout.isRefreshing = false
            }, 10000)
            CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
                viewModel.getPopularMovies()

                withContext(Dispatchers.Main){
                    binding.recyclerview.adapter?.notifyDataSetChanged()
                    binding.refreshLayout.isRefreshing = false
                }
            }
        }
    }

    private fun initRecyclerview(list: List<Movie>){
        binding.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        var adapter = MovieAdapter(application, list)
        binding.recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
