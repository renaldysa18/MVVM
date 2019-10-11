package com.redveloper.mvvm.ui.home.quote

import com.redveloper.mvvm.R
import com.redveloper.mvvm.data.db.entities.QuoteModel
import com.redveloper.mvvm.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: QuoteModel
) : BindableItem<ItemQuoteBinding>(){
    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}