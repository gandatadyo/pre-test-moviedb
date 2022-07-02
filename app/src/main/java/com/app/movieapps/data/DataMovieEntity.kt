package com.app.movieapps.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DataMovieEntity(
    var id: String?,
    var original_title: String?,
    var release_date: String?,
    var overview: String?,
    var poster_path: String?
) : Parcelable
