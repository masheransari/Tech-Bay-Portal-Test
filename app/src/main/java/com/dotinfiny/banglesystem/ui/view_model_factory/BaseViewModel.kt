package com.dotinfiny.banglesystem.ui.view_model_factory

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dotinfiny.banglesystem.MainApplication
import com.dotinfiny.banglesystem.model.local.LiveResultModel
import com.dotinfiny.banglesystem.repository.DatabaseRepository
import com.org.dotinfiny.gamesprime.data.AppRepository
import com.org.dotinfiny.gamesprime.model.api.ResultModel

open class BaseViewModel : ViewModel() {



//    var appRepository: AppRepository
//
//    private var response: MutableLiveData<LiveResultModel> = MutableLiveData<LiveResultModel>()
//
//    var dbRepository: DatabaseRepository
//
//    init {
//        appRepository = AppRepository(MainApplication.getAppContext()!!)
//        dbRepository = DatabaseRepository(MainApplication.getAppContext())
//    }
//
//    fun getViewModelResponse(): LiveData<LiveResultModel> {
//        return response
//    }
//
//    fun getApiResult(): LiveData<ResultModel> {
//        return appRepository.resultData
//    }
//
//    fun initFunction() {
//        Toast.makeText(MainApplication.getAppContext(), "testing Content", Toast.LENGTH_SHORT)
//            .show()
//    }


}