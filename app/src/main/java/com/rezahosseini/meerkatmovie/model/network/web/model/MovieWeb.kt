package com.rezahosseini.meerkatmovie.model.network.web.model

import java.io.Serializable
import com.google.gson.annotations.SerializedName
data class MovieWeb(
    @SerializedName("id")
    val id :Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("year")
    val year:Int
): Serializable