package com.redveloper.mvvm.data.network.responses

import com.redveloper.mvvm.data.db.entities.UsersModel

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: UsersModel?
)