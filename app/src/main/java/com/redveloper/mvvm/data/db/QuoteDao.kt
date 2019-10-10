package com.redveloper.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.redveloper.mvvm.data.db.entities.QuoteModel

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuote(quote: List<QuoteModel>)

    @Query("SELECT * FROM quotemodel")
    fun getQuoteAll() : LiveData<List<QuoteModel>>
}