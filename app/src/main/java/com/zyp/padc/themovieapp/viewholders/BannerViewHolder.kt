package com.zyp.padc.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zyp.padc.themovieapp.data.vos.MovieVO
import com.zyp.padc.themovieapp.delegates.BannerViewHolderDelegate
import com.zyp.padc.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_item_banner.view.*

class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let { movie ->
                mDelegate.onTabMovieFromBanner(movie.id)
            }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.backdropPath}")
            .into(itemView.ivBannerImage)

        itemView.tvBannerMovieName.text = movie.title
    }

}