package com.redveloper.mvvm.iview

import com.redveloper.mvvm.data.db.entities.UsersModel

interface AuthView {
        fun onSuccess(loginResponse: UsersModel?)
        fun onFailure(message: String)
        fun onStarted()
        fun showLoading()
        fun hideLoading()
}