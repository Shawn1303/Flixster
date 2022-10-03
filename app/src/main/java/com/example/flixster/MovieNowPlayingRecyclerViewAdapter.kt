package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.R.id

class MovieNowPlayingRecyclerViewAdapter (
    private val movies: List<MovieNowPlaying>,
    private val mListener: OnListFragmentInteractionListener?
    )
    : RecyclerView.Adapter<MovieNowPlayingRecyclerViewAdapter.BookViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_movie_now_playing, parent, false)
            return BookViewHolder(view)
        }

        /**
         * This inner class lets us refer to all the different View elements
         * (Yes, the same ones as in the XML layout files!)
         */
        inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            var mItem: MovieNowPlaying? = null
            val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
            val mMovieOverview: TextView = mView.findViewById<View>(id.overview) as TextView
            val mMovieImage: ImageView = mView.findViewById<View>(id.movie_image) as ImageView


            override fun toString(): String {
                return mMovieTitle.toString()
            }
        }

        /**
         * This lets us "bind" each Views in the ViewHolder to its' actual data!
         */
        override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
            val movie = movies[position]

            holder.mItem = movie
            holder.mMovieTitle.text = movie.title
            holder.mMovieOverview.text = movie.overview

            Glide.with(holder.mView)
                .load("https://image.tmdb.org/t/p/w500" + movie.movieImageURL)
                .centerInside()
                .into(holder.mMovieImage)

            holder.mView.setOnClickListener {
                holder.mItem?.let { movie ->
                    mListener?.onItemClick(movie)
                }
            }
        }

        /**
         * Remember: RecyclerView adapters require a getItemCount() method.
         */
        override fun getItemCount(): Int {
            return movies.size
        }
}