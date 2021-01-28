package app.dotinfiny.interviewtask.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dotinfiny.banglesystem.Utils.HawkUtil
import com.org.dotinfiny.gamesprime.data.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ApiCall(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    //    private val databaseDao = AppRepository(appContext)..getDatabase(MainApplication.getAppContext()!!)
    private val apiRepository = AppRepository(appContext)

    private val applicationScope = CoroutineScope(Dispatchers.Main)

    companion object {
        const val WORK_NAME = "SYNC_API"
    }

    override suspend fun doWork(): Result {
        Timber.d("Call Do Work")
        applicationScope.launch {
            if (HawkUtil.getInstance(applicationContext).pendingEnteries != null) {
                apiRepository.getData(HawkUtil.getInstance(applicationContext).pendingEnteries)
                    .let {
                        //print success message
                    }
            }
        }
        return Result.success()
    }
}