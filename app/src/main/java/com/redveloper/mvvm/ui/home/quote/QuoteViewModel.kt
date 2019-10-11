package com.redveloper.mvvm.ui.home.quote

import androidx.lifecycle.ViewModel
import com.redveloper.mvvm.data.repositories.QuoteRespository
import com.redveloper.mvvm.utils.lazyDeferred

class QuoteViewModel(
   repository: QuoteRespository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
