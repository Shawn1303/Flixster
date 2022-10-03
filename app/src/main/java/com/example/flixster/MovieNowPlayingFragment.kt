package com.example.flixster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject


private const val API_KEY = "afe50f44d03f4fe402a0c7648c92007a"

class MovieNowPlayingFragment : Fragment(), OnListFragmentInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_now_playing_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY
        // Using the client, perform the HTTP request
        client["https://api.themoviedb.org/3/movie/now_playing", params, object : JsonHttpResponseHandler()
                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers?,
//                        json: JsonHttpResponseHandler.JSON
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        Log.i("error", json.toString())
                        // The wait for a response is over
                        progressBar.hide()
//                        val resultsJSON : String = json.jsonObject.get("results") as JSONObject
                        val movieRawJSON : String = json.jsonObject.get("results").toString()
                        Log.i("showString", movieRawJSON)
                        val gson = Gson()
                        val arrayMovieType = object : TypeToken<List<MovieNowPlaying>>() {}.type
                        val models : List<MovieNowPlaying> = gson.fromJson(movieRawJSON, arrayMovieType) // Fix me!
                        recyclerView.adapter = MovieNowPlayingRecyclerViewAdapter(models, this@MovieNowPlayingFragment)

                        // Look for this in Logcat:
                        Log.d("MovieNowPlayingFragment", "response successful")
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("MovieNowPlayingFragment", errorResponse)
                        }
                    }
                }]


    }

    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(item: MovieNowPlaying) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }

}