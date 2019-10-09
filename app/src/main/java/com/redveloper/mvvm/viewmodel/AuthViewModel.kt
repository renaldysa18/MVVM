package com.redveloper.mvvm.viewmodel

import android.content.Intent
import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModel
import com.redveloper.mvvm.data.repositories.UserRespository
import com.redveloper.mvvm.iview.AuthView
import com.redveloper.mvvm.ui.auth.LoginActivity
import com.redveloper.mvvm.ui.auth.SignUpActivity
import com.redveloper.mvvm.utils.ApiException
import com.redveloper.mvvm.utils.Coroutines

class AuthViewModel(
    private val repository: UserRespository
) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var conf_password: String? = null
    var name: String? = null

    var authListener: AuthView? = null

    fun getLoggedInUser() = repository.getUser()

    fun onButtonLoginClick(view: View) {
        authListener?.onStarted()
        //validasi email & password
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                }
            } catch (e: ApiException) {
                e.message?.let { authListener?.onFailure(it) }
            }
        }


    }

    fun onButtonSignUpClick(view: View){
        authListener?.onStarted()
        //validasi name
        if(name.isNullOrEmpty()){
            authListener?.onFailure("name is empety")
            return
        }
        //validasi email
        if(email.isNullOrEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            authListener?.onFailure("email is not valid")
            return
        }
        //validasi password
        if(password.isNullOrEmpty()){
            authListener?.onFailure("password is empety")
            return
        }
        //validasi conf password
        if(conf_password != password){
            authListener?.onFailure("confirm password is not same")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userSignUp(name!!,email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                }

            }catch (e: ApiException) {
                e.message?.let { authListener?.onFailure(it) }
            }
        }
    }

    //go to
    fun onButtonToLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onButtonToRegister(view: View){
        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

}