package com.app.movieapps.data

import android.os.Parcelable
import com.app.movieapps.data.remote.response.AuthorDetails
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class DataReviewEntity(
    val id: String? = null,
    val updatedAt: String? = null,
    val author: String? = null,
    val createdAt: String? = null,
    val content: String? = null,
    val url: String? = null
) : Parcelable
