package com.redveloper.mvvm.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redveloper.mvvm.data.repositories.UserRespository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val repository: UserRespository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}