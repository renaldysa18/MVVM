package com.redveloper.mvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.redveloper.mvvm.R
import com.redveloper.mvvm.data.db.entities.UsersModel
import com.redveloper.mvvm.databinding.ActivityLoginBinding
import com.redveloper.mvvm.iview.AuthView
import com.redveloper.mvvm.ui.home.HomeActivity
import com.redveloper.mvvm.utils.hide
import com.redveloper.mvvm.utils.show
import com.redveloper.mvvm.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthView, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.loginViewModel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { UsersModel ->
            if (UsersModel != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }

    override fun onSuccess(user: UsersModel?) {
        hideLoading()
    }

    override fun onFailure(message: String) {
        hideLoading()
        toast(message)
    }

    override fun onStarted() {
        showLoading()
    }

    override fun showLoading() {
        pb_login.show()
    }

    override fun hideLoading() {
        pb_login.hide()
    }
}
