package com.example.moviesapp.presentation.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.RecyclerRowBinding
import com.example.moviesapp.domain.entities.Movie

class MovieAdapter(val app: Application, var list: List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(val binding: RecyclerRowBinding): ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var model = list[position]
        holder.binding.model = model

        Glide.with(app).load("https://image.tmdb.org/t/p/w500/${model.posterPath}").into(holder.binding.imageview)
    }

}