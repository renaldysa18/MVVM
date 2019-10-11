package com.redveloper.mvvm.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pixplicity.easyprefs.library.Prefs
import com.redveloper.mvvm.data.db.AppDatabase
import com.redveloper.mvvm.data.db.entities.QuoteModel
import com.redveloper.mvvm.data.network.BaseApi
import com.redveloper.mvvm.data.network.SafeApiRequest
import com.redveloper.mvvm.utils.Coroutines
import com.redveloper.mvvm.utils.StaticString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


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

        val savedAt = Prefs.getString(StaticString.KEY_SAVED_AT, null)

        if(isFetchNeeded(LocalDateTime.parse(savedAt)) || savedAt == null ){
            try {
                val response = apiRequest { api.getQoutes() }
                quotes.postValue(response.quotes)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun isFetchNeeded(saveAt: LocalDateTime): Boolean{
        return ChronoUnit.HOURS.between(saveAt, LocalDateTime.now()) > 6
    }

    private fun saveQuotes(quotes: List<QuoteModel>){
        Coroutines.io {
            Prefs.putString(StaticString.KEY_SAVED_AT, "hai")
            db.getQuoteDao().insertQuote(quotes)
        }
    }
}