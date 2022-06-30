package com.aldikitta.movplaypt2.data.remote.responses.tvshowresponses

import com.aldikitta.movplaypt2.model.tvshow.TvShow
import com.google.gson.annotations.SerializedName

data class TVShowResponse (
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvShow>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
    )
