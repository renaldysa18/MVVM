package com.redveloper.mvvm.ui.home.profil

import androidx.lifecycle.ViewModel
import com.redveloper.mvvm.data.repositories.UserRespository

class ProfileViewModel(
    repository: UserRespository
) : ViewModel() {

    val user = repository.getUser()


}
