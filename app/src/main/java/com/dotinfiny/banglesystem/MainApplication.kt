package com.dotinfiny.banglesystem

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.work.*
import app.dotinfiny.interviewtask.workmanager.ApiCall
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class MainApplication : Application() {

    val applicationScope = CoroutineScope(Dispatchers.Default)

    companion object {
        private var context: Context? = null

        fun getAppContext(): Context? {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        context = applicationContext
        setupRecurringWork()
    }

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }
            .build()

//        It will run after every 1 hours to sync ids from server
        val repeatRequest = PeriodicWorkRequestBuilder<ApiCall>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            ApiCall.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatRequest
        )
    }
}