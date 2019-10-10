package com.redveloper.mvvm.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.redveloper.mvvm.data.db.AppDatabase
import com.redveloper.mvvm.data.db.entities.QuoteModel
import com.redveloper.mvvm.data.network.BaseApi
import com.redveloper.mvvm.data.network.SafeApiRequest
import com.redveloper.mvvm.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRespository (
    private val api: BaseApi,
    private val db: AppDatabase
) : SafeApiRequest(){
    private val quotes = MutableLiveData<List<QuoteModel>>()

    init {

        quotes.observeForever{
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<QuoteModel>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuoteAll()
        }
    }

    private suspend fun fetchQuotes(){
        if(isFetchNeeded()){
            val response = apiRequest { api.getQoutes() }
            quotes.postValue(response.quotes)

        }
    }

    private fun isFetchNeeded(): Boolean{
        return true
    }

    fun saveQuotes(quotes: List<QuoteModel>){
        Coroutines.io {
            db.getQuoteDao().insertQuote(quotes)
        }
    }
}