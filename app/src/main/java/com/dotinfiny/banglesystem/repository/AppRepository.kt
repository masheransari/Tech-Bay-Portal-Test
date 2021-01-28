package com.org.dotinfiny.gamesprime.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.dotinfiny.banglesystem.Utils.Constants
import com.dotinfiny.banglesystem.model.api.CommentModel
import com.dotinfiny.banglesystem.model.api.PostModel
import com.dotinfiny.banglesystem.retrofit.AppUtil
import com.dotinfiny.banglesystem.retrofit.RetrofitBaseCallBack
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.org.dotinfiny.gamesprime.helpers.RequestID
import com.org.dotinfiny.gamesprime.model.api.ResultModel
import com.org.dotinfiny.gamesprime.retrofit.BangleService
import com.orhanobut.hawk.Hawk
import java.lang.reflect.Type

class AppRepository(ctx: Context) {

    private val mService: BangleService
    var resultData: MutableLiveData<ResultModel> = MutableLiveData()
    private var gson = Gson()

    init {

        Hawk.init(ctx).build()

//        if (Hawk.get<LoginSignupResponse>("loginResponse") != null) {
//            loggedInUser = Hawk.get<LoginSignupResponse>("loginResponse")
//            hm["Authorization"] = loggedInUser?.token.toString()
//        }


        mService = AppUtil.getBangleService() as BangleService
    }

    suspend fun getData(ids: String?) {
        mService.getData("data/"+ids).enqueue(object : RetrofitBaseCallBack<String>() {
            override fun onResponse(response: String?, error: Error?) {
                if (error == null) {

                } else {
                }
            }
        })
    }

    fun createPostRequest(requestID: RequestID, id: String?) {
        mService.getPost(
            getEndoint(requestID, id!!)
        ).enqueue(object : RetrofitBaseCallBack<String>() {
            override fun onResponse(response: String?, error: Error?) {
                if (error == null) {
                    when (requestID) {
                        RequestID.REQ_POST -> {
                            val collectionType: Type =
                                object : TypeToken<ArrayList<PostModel>>() {}.type

                            val resultModel =
                                Gson().fromJson<ArrayList<PostModel>>(response, collectionType)

                            var model = ResultModel(true, null, resultModel, null, requestID)
                            resultData.value = model
                        }
                        RequestID.REQ_COMMENT -> {
                            val collectionType: Type =
                                object : TypeToken<ArrayList<CommentModel>>() {}.type

                            val resultModel =
                                Gson().fromJson<ArrayList<CommentModel>>(response, collectionType)

                            var model = ResultModel(true, null, resultModel, null, requestID)
                            resultData.value = model
                        }
                    }
                } else {
                    resultData.value = getCustomErrorResponse(requestID)
                }
            }
        })
    }

    fun getCustomErrorResponse(requestID: RequestID): ResultModel {
        var result = ResultModel(
            false,
            "Something went wrong\n Please check your internet connection",
            null,
            null,
            requestID
        )
        return result
    }

    fun getEndoint(requestID: RequestID, id: String): String {

        var URL = ""
        URL = when (requestID) {
            RequestID.REQ_POST -> Constants.API_POST
            RequestID.REQ_COMMENT -> Constants.API_COMMENT.replace("{post_id}", id)
        }
        return URL
    }
}

