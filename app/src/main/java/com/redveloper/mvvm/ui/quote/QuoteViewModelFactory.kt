package com.redveloper.mvvm.ui.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redveloper.mvvm.data.repositories.QuoteRespository

class QuoteViewModelFactory (
    val repository: QuoteRespository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuoteViewModel(repository) as T
    }
}