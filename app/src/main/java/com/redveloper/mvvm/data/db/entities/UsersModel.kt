package com.redveloper.mvvm.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_USER_ID = 0

@Entity
data class UsersModel(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("email_verified_at")
    var email_verified_at: String? = "",
    @SerializedName("created_at")
    var created_at: String? = "",
    @SerializedName("updated_at")
    var updated_at: String? = ""
){
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}