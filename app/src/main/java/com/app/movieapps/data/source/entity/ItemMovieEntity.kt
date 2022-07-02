package com.app.movieapps.data.source.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class ItemMovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "itemId")
    var itemId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "dateItem")
    var dateItem: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "imagePath")
    var imagePath: String
)