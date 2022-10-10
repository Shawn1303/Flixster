package com.example.flixster

import com.google.gson.annotations.SerializedName

class MovieReviewData {
    @SerializedName("author")
    var author: String? = null

    @SerializedName("content")
    var content: String? = null
}