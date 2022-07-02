package com.app.movieapps.data

import android.os.Parcelable
import com.app.movieapps.data.remote.response.AuthorDetails
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class DataReviewEntity(
    val name: String? = null,
    val username: String? = null,
    val avatar_path: String? = null,
    val rating: String? = null,
) : Parcelable
