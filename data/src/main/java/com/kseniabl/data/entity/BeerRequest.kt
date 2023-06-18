package com.kseniabl.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class BeerRequest (
    @PrimaryKey
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    @SerializedName("image_url")
    val imageUrl: String? = null,
)