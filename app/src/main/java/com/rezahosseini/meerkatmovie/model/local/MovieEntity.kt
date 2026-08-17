package com.rezahosseini.meerkatmovie.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val name:String,
    val year:Int
)