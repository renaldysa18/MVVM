package com.redveloper.mvvm.ui.home.profil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redveloper.mvvm.data.repositories.UserRespository

class ProfileViewModelFactory(
    private val repository: UserRespository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}