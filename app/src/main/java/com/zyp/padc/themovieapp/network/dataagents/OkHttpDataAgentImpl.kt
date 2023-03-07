package com.zyp.padc.themovieapp.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.zyp.padc.themovieapp.data.vos.MovieVO
import com.zyp.padc.themovieapp.network.responses.MovieListResponse
import com.zyp.padc.themovieapp.utils.API_GET_NOW_PLAYING
import com.zyp.padc.themovieapp.utils.BASE_URL
import com.zyp.padc.themovieapp.utils.MOVIE_API_KEY
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object OkHttpDataAgentImpl : MovieDataAgent {

    private val mClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    override fun getNowPlayingMovies() {
        GetNowPlayingMoviesOkHttpTask(mClient).execute()
    }

    class GetNowPlayingMoviesOkHttpTask(
        private val mOkHttpClient: OkHttpClient
    ) :
        AsyncTask<Void, Void, MovieListResponse?>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {

            val request = Request.Builder()
                .url("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")
                .build()

            try {

                val response = mOkHttpClient.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.let {
                        val responseString = it.string()
                        val movieListResponse = Gson().fromJson(
                            responseString, MovieListResponse::class.java
                        )

                        return movieListResponse
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Error", e.message ?: "")
            }
            return null
        }

        override fun onPostExecute(result: MovieListResponse?) {
            super.onPostExecute(result)
        }

    }

}

