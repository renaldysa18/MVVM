package com.redveloper.mvvm.ui.home.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redveloper.mvvm.data.repositories.QuoteRespository

@Suppress("UNCHECKED_CAST")
class QuoteViewModelFactory (
    val repository: QuoteRespository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuoteViewModel(repository) as T
    }
}