package com.redveloper.mvvm.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class QuoteModel(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("quote")
    val quote: String?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("created_at")
    val created_at: String?,
    @SerializedName("updated_at")
    val updated_at: String?
)