package com.redveloper.mvvm.ui.quote

import androidx.lifecycle.ViewModel
import com.redveloper.mvvm.data.repositories.QuoteRespository
import com.redveloper.mvvm.utils.lazyDefered

class QuoteViewModel(
    repository: QuoteRespository
) : ViewModel() {

    val quotes by lazyDefered {
        repository.getQuotes()
    }
}
