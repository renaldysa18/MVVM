package com.redveloper.mvvm.data.repositories

import com.redveloper.mvvm.data.db.AppDatabase
import com.redveloper.mvvm.data.db.entities.UsersModel
import com.redveloper.mvvm.data.network.BaseApi
import com.redveloper.mvvm.data.network.SafeApiRequest
import com.redveloper.mvvm.data.network.responses.AuthResponse

class UserRespository(
    private val api: BaseApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest {
            api.userLogin(email, password)
        }
    }

    suspend fun userSignUp(name: String, email: String, password: String): AuthResponse{
        return apiRequest {
            api.userSignUp(name, email, password)
        }
    }

    suspend fun saveUser(user: UsersModel) = db.getUserDao().insert(user)

    fun getUser() = db.getUserDao().getUser()
}