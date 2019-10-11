package com.redveloper.mvvm

import android.app.Application
import com.redveloper.mvvm.data.db.AppDatabase
import com.redveloper.mvvm.data.network.BaseApi
import com.redveloper.mvvm.data.network.NetworkConnectionInterceptor
import com.redveloper.mvvm.data.repositories.QuoteRespository
import com.redveloper.mvvm.data.repositories.UserRespository
import com.redveloper.mvvm.ui.auth.AuthViewModelFactory
import com.redveloper.mvvm.ui.home.profil.ProfileViewModelFactory
import com.redveloper.mvvm.ui.home.quote.QuoteViewModelFactory
import com.redveloper.mvvm.utils.PrefsApplication
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : PrefsApplication(), KodeinAware{
    override val kodein = Kodein.lazy {
            import(androidXModule(this@MVVMApplication))

            bind() from singleton { NetworkConnectionInterceptor(instance()) }
            bind() from singleton { BaseApi(instance())}
            bind() from singleton { AppDatabase(instance()) }
            bind() from singleton { UserRespository(instance(), instance()) }
            bind() from singleton { QuoteRespository(instance(), instance()) }
            bind() from provider { AuthViewModelFactory(instance()) }
            bind() from provider { ProfileViewModelFactory(instance()) }
            bind() from provider { QuoteViewModelFactory(instance()) }
        }
}