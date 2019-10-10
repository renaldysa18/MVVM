package com.redveloper.mvvm.data.network.responses

import com.redveloper.mvvm.data.db.entities.QuoteModel

data class QuoteResponse(
    val isSuccessful: Boolean?,
    val quotes: List<QuoteModel>
)