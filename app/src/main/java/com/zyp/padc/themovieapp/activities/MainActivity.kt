package com.zyp.padc.themovieapp.activities

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.zyp.padc.themovieapp.R
import com.zyp.padc.themovieapp.adapters.BannerAdapter
import com.zyp.padc.themovieapp.adapters.ShowcaseAdapter
import com.zyp.padc.themovieapp.data.models.MovieModel
import com.zyp.padc.themovieapp.data.models.MovieModelImpl
import com.zyp.padc.themovieapp.data.vos.GenreVO
import com.zyp.padc.themovieapp.delegates.BannerViewHolderDelegate
import com.zyp.padc.themovieapp.delegates.MovieViewHolderDelegate
import com.zyp.padc.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.zyp.padc.themovieapp.viewpods.ActorListViewPod
import com.zyp.padc.themovieapp.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerViewHolderDelegate, ShowcaseViewHolderDelegate,
    MovieViewHolderDelegate {

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMoviesByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

    /// Model
    private val mMovieModel: MovieModel = MovieModelImpl

    private var mGenres: List<GenreVO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolBar()
        setUpViewPods()
        setUpBannerViewPager()
//        setUpGenreTabLayout()
        setUpShowcaseRecyclerView()
        setUpListeners()

        requestData()

//        MovieDataAgentImpl.getNowPlayingMovies()
//        OkHttpDataAgentImpl.getNowPlayingMovies()
//        RetrofitDataAgentImpl.getNowPlayingMovies()

    }

    private fun requestData() {
        //Now Playing Movies
        mMovieModel.getNowPlayingMovies(
            onSuccess = {
                mBannerAdapter.setNewData(it)
            }, onFailure = {
                showSnackBar(it)
            }
        )

        //Popular Movies
        mMovieModel.getPopularMovies(
            onSuccess = {
                mBestPopularMovieListViewPod.setData(it)
            }, onFailure = {
                showSnackBar(it)
            }
        )

        //Top Rated Movies
        mMovieModel.getTopRatedMovies(
            onSuccess = {
                mShowcaseAdapter.setNewData(it)
            }, onFailure = {
                showSnackBar(it)
            }
        )

        //Get Genres
        mMovieModel.getGenres(
            onSuccess = { it ->
                mGenres = it
                setUpGenreTabLayout(it)

                //getMoviesByGenreId
                it.firstOrNull()?.id?.let { genreId ->
                    getMoviesByGenre(genreId)
                }

            }, onFailure = {
                showSnackBar(it)
            }
        )

        //Get Actors
        mMovieModel.getActors(
            onSuccess = {
                mActorListViewPod.setData(it)
            }, onFailure = {
                showSnackBar(it)
            }
        )


    }

    private fun getMoviesByGenre(genreId: Int) {
        mMovieModel.getMoviesByGenre(
            genreId = genreId.toString(),
            onSuccess = {
                mMoviesByGenreViewPod.setData(it)

            }, onFailure = {
                showSnackBar(it)
            }
        )
    }

    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)

        mMoviesByGenreViewPod = vpMoviesByGenre as MovieListViewPod
        mMoviesByGenreViewPod.setUpMovieListViewPod(this)

        mActorListViewPod = vpActorList as ActorListViewPod

    }

    private fun setUpListeners() {
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                showSnackBar((tab?.text ?: "") as String)
                mGenres?.get(tab?.position ?: 0)?.id?.let {
                    getMoviesByGenre(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(this)
        viewPagerBanner.adapter = mBannerAdapter

        dotsIndicatorBanner.attachTo(viewPagerBanner)

    }

    private fun setUpGenreTabLayout(genres: List<GenreVO>) {
        genres.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
//            val tab = tabLayoutGenre.newTab()
//            tab.text = it
//            tabLayoutGenre.addTab(tab)
        }
    }

    private fun setUpShowcaseRecyclerView() {
        mShowcaseAdapter = ShowcaseAdapter(this)
        rvShowCases.layoutManager =
            LinearLayoutManager(
                applicationContext, LinearLayoutManager.HORIZONTAL,
                false
            )
        rvShowCases.adapter = mShowcaseAdapter


    }

    private fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onTabMovieFromBanner(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
//        showSnackBar("Tap Movie Form Banner")
    }

    override fun onTabMovieFromShowcase(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
//        showSnackBar("Tap Movie Form Showcase")
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
//        showSnackBar("Tap Movie Form Best Popular or Movies by Genre")
    }

}