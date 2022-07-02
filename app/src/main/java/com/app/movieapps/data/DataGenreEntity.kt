package com.app.movieapps.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DataGenreEntity(
    var id: String?,
    var name: String?,
) : Parcelable
