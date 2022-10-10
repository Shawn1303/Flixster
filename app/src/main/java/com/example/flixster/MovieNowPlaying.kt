package com.example.flixster

import com.google.gson.annotations.SerializedName

class MovieNowPlaying {
    @SerializedName("title")
    var title: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("poster_path")
    var movieImageURL:String? = null

    @SerializedName("id")
    var movieId:Int? = null
}