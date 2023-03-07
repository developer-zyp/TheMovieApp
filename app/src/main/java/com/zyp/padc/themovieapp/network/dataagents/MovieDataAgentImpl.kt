package com.zyp.padc.themovieapp.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.zyp.padc.themovieapp.data.vos.MovieVO
import com.zyp.padc.themovieapp.network.responses.MovieListResponse
import com.zyp.padc.themovieapp.utils.API_GET_NOW_PLAYING
import com.zyp.padc.themovieapp.utils.BASE_URL
import com.zyp.padc.themovieapp.utils.MOVIE_API_KEY
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


object MovieDataAgentImpl : MovieDataAgent {
    override fun getNowPlayingMovies() {
        GetNowPlayingMoviesTask().execute()
    }

    class GetNowPlayingMoviesTask : AsyncTask<Void, Void, MovieListResponse?>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val url: URL
            var reader: BufferedReader? = null
            val stringBuilder: StringBuilder

            try {
                //create HttpURLConnection
                url =
                    URL("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                //set Http Method
                connection.requestMethod = "GET"

                //wait 15s to response
                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                //read the output from server
                reader = BufferedReader(InputStreamReader(connection.inputStream))

                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("NowPlayingMovies", responseString)

                val movieListResponse = Gson().fromJson(
                    responseString, MovieListResponse::class.java
                )

                return movieListResponse

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Error :", e.message ?: "")
            }
            return null
        }

        override fun onPostExecute(result: MovieListResponse?) {
            super.onPostExecute(result)
        }

    }

}
