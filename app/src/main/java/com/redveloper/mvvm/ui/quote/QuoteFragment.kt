package com.redveloper.mvvm.ui.quote

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.redveloper.mvvm.R
import com.redveloper.mvvm.data.db.AppDatabase
import com.redveloper.mvvm.data.network.BaseApi
import com.redveloper.mvvm.data.repositories.QuoteRespository
import com.redveloper.mvvm.utils.Coroutines
import com.redveloper.mvvm.utils.toast

class QuoteFragment : Fragment() {

    private lateinit var viewModel: QuoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quote_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = BaseApi.invoke()
        val db = context?.applicationContext?.let { AppDatabase(it) }
        val repository = db?.let { QuoteRespository(api, it) }
        val factory = repository?.let { QuoteViewModelFactory(it) }
        viewModel = ViewModelProviders.of(this, factory).get(QuoteViewModel::class.java)

        Coroutines.main {
            val quotes = viewModel.quotes.await()
            quotes.observe(this, Observer {
                context?.toast(it.size.toString())
            })
        }
    }

}
