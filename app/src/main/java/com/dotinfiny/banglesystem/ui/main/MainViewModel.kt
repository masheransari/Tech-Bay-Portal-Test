package com.dotinfiny.banglesystem.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.dotinfiny.banglesystem.repository.DatabaseRepository
import com.dotinfiny.banglesystem.ui.view_model_factory.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    val appDatabase: DatabaseRepository
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            appDatabase.removeAllTables()
        }
    }

}