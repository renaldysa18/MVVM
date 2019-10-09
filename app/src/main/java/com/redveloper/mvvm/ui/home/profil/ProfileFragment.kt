package com.redveloper.mvvm.ui.home.profil

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.redveloper.mvvm.R
import com.redveloper.mvvm.data.db.AppDatabase
import com.redveloper.mvvm.data.network.BaseApi
import com.redveloper.mvvm.data.repositories.UserRespository
import com.redveloper.mvvm.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val api = BaseApi.invoke()
        val db = context?.let { AppDatabase(it) }
        val repository = db?.let { UserRespository(api, it) }
        val factory = repository?.let { ProfileViewModelFactory(it) }

        val binding: ProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}
