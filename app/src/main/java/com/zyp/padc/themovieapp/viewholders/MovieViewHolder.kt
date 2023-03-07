package com.zyp.padc.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zyp.padc.themovieapp.data.vos.MovieVO
import com.zyp.padc.themovieapp.delegates.MovieViewHolderDelegate
import com.zyp.padc.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MovieViewHolder(itemView: View, private val mDelegate: MovieViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let { it1 -> mDelegate.onTapMovie(it1.id) }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivMovieImage)

        itemView.tvMovieDetailsName.text = movie.title
        itemView.tvMovieRating.text = movie.voteAverage.toString()
        itemView.rbMovieRating.rating = movie.getRatingBasedOnFiveStars()

    }

}