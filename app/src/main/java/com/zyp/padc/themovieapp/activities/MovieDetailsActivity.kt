package com.zyp.padc.themovieapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.zyp.padc.themovieapp.R
import com.zyp.padc.themovieapp.data.models.MovieModel
import com.zyp.padc.themovieapp.data.models.MovieModelImpl
import com.zyp.padc.themovieapp.data.vos.GenreVO
import com.zyp.padc.themovieapp.data.vos.MovieVO
import com.zyp.padc.themovieapp.utils.IMAGE_BASE_URL
import com.zyp.padc.themovieapp.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)
            return intent
        }
    }

    //ViewPod
    lateinit var actorsViewPod: ActorListViewPod
    lateinit var creatorsViewPod: ActorListViewPod

    // Model
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setUpViewPods()
        setUpListeners()

        val movieId = intent?.getIntExtra(MOVIE_ID, 0)

        movieId?.let {
            requestData(it)
        }

    }

    private fun requestData(movieId: Int) {
        mMovieModel.getMovieDetails(
            movieId = movieId.toString(),
            onSuccess = {
                bindData(it)
            }, onFailure = {
                showSnackBar(it)
            }
        )

        mMovieModel.getCreditsByMovie(
            movieId = movieId.toString(),
            onSuccess = {
                actorsViewPod.setData(it.first)
                creatorsViewPod.setData(it.second)
            }, onFailure = {
                showSnackBar(it)
            }
        )

    }

    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.backdropPath}")
            .into(ivMovieDetails)

        toolBar.title = movie.title
        tvMovieDetailsName.text = movie.title
        tvReleaseYear.text = movie.releaseDate?.substring(0, 4)
        tvRating.text = movie.voteAverage.toString()
        movie.voteCount?.let {
            tvNumberOfVotes.text = "$it VOTES"
        }
        rbMovieDetails.rating = movie.getRatingBasedOnFiveStars()

        bindGenre(movie, movie.genres ?: listOf())

        movie.runtime?.let {
            tvTime.text = "$it MINS"
        }

        tvOverview.text = movie.overview
        tvOriginalTitle.text = movie.title
        tvType.text = movie.getGenresAsCommaSeparatedString()
        tvProduction.text = movie.getProductionCountryAsCommaSeparatedString()
        tvPremiere.text = movie.releaseDate
        tvDescription.text = movie.overview

    }

    private fun bindGenre(movie: MovieVO, genres: List<GenreVO>) {
        movie.genres?.size?.let {
            tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""

            if (it < 3) {
                tvThirdGenre.visibility = View.GONE
            } else if (it < 2) {
                tvSecondGenre.visibility = View.GONE
            }

        }
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPods() {
        actorsViewPod = vpActors as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )

        creatorsViewPod = vpCreators as ActorListViewPod
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )

    }

    private fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }

}