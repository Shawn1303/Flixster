package com.example.flixster

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.ContentLoadingProgressBar
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

class MovieReview : AppCompatActivity() {
//    private lateinit var movieImageView: ImageView
//    private lateinit var movieTitleTextView: TextView
//    private lateinit var movieReviewTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_review)

    val movieImageView = findViewById<ImageView>(R.id.movie_image)
    val movieTitleTextView = findViewById<TextView>(R.id.movie_title)
    val movieReviewTextView = findViewById<TextView>(R.id.movie_review)

    movieTitleTextView.text = intent.getStringExtra("movieTitle").toString()
    movieReviewTextView.text = intent.getStringExtra("moviereviewcontent").toString(
    Glide.with(this@MovieReview)
        .load("https://image.tmdb.org/t/p/w500" + intent.getStringExtra("movieImageUrl").toString())
        .into(movieImageView)


//        val client = AsyncHttpClient()
//        val movieID = intent.getStringExtra("movieID")
//        val progressBar = findViewById<View>(R.id.progress) as ContentLoadingProgressBar
//        progressBar.show()
//        client.get("https://api.themoviedb.org/3/movie/$movieID/reviews?api_key=afe50f44d03f4fe402a0c7648c92007a", object : JsonHttpResponseHandler() {
//            override fun onFailure(
//                statusCode: Int,
//                headers: Headers?,
//                response: String?,
//                throwable: Throwable?
//            ) {
//                progressBar.hide()
//
//                Log.e(TAG, "Failed to fetch movie review: $statusCode")
//            }
//
//            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
//                Log.i(TAG, "Successfully fetched movie review: $json")
//                try {
//                    progressBar.hide()
//                    val movieReviewString : String = json.jsonObject.get("content").toString()
//                    Log.i("showString", movieReviewString)
//                    movieImageView = findViewById(R.id.movie_image)
//                    movieTitleTextView = findViewById(R.id.movie_title)
//                    movieReviewTextView = findViewById(R.id.movie_review)
//
//                    movieTitleTextView.text = getIntent().getStringExtra("movieTitle")
//                    movieReviewTextView.text = movieReviewString
//
//                    Glide.with(this@MovieReview)
//                        .load("https://image.tmdb.org/t/p/w500" + intent.getStringExtra("movieImageUrl"))
//                        .into(movieImageView)
//                } catch (e: JSONException) {
//                    Log.e(TAG, "Exception: $e")
//                }
//            }
//        })


    }
}