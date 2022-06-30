package com.aldikitta.movplaypt2.data.remote.responses

import android.os.Parcelable
import com.aldikitta.movplaypt2.model.Cast
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreditResponse(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("id")
    val id: Int
) : Parcelable
