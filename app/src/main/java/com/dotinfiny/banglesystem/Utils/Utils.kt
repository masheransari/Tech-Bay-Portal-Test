package com.dotinfiny.banglesystem.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.dotinfiny.banglesystem.model.api.PostModel
import com.dotinfiny.banglesystem.model.local.DbPostModel

class Utils {
    companion object {
        fun getDbPostModel(model: PostModel): DbPostModel {
            val dbModel = DbPostModel()
            dbModel.body = model.body
            dbModel.id = model.id
            dbModel.title = model.title
            dbModel.userId = model.userId
            return dbModel
        }

        fun getPostModel(dbModel: DbPostModel): PostModel {
            val model = PostModel()
            model.body = dbModel.body
            model.id = dbModel.id
            model.title = dbModel.title
            model.userId = dbModel.userId
            return model
        }

        fun isConnected(context: Context): Boolean {
            var result = false
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (cm != null) {
                    val capabilities =
                        cm.getNetworkCapabilities(cm.activeNetwork)
                    if (capabilities != null) {
                        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                            result = true
                        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            result = true
                        }
                    }
                }
            } else {
                if (cm != null) {
                    val activeNetwork = cm.activeNetworkInfo
                    if (activeNetwork != null) {
                        // connected to the internet
                        if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                            result = true
                        } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                            result = true
                        }
                    }
                }
            }
            return result
        }
    }
}