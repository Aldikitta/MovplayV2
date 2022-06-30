package com.aldikitta.movplaypt2.data.remote.responses

import com.aldikitta.movplaypt2.model.Genre
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)