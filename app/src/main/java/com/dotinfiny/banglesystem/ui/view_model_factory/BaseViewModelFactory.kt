package com.dotinfiny.banglesystem.ui.view_model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dotinfiny.banglesystem.helpers.SharedViewModel
import com.dotinfiny.banglesystem.ui.login.LoginViewModel
import com.dotinfiny.banglesystem.ui.main.saved.FavouriteViewModel

class BaseViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel() as T
        } else if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}