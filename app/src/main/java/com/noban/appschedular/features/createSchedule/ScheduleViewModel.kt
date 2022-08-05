package com.noban.appschedular.features.createSchedule

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.WorkManager
import com.noban.appschedular.core.platform.BaseViewModel
import com.noban.appschedular.core.worker.AppScheduleWorker
import com.noban.appschedular.data.model.ScheduleEntity
import com.noban.appschedular.data.repository.ScheduleRepository
import com.noban.appschedular.util.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class ScheduleViewModel  @Inject constructor(private val scheduleRepository: ScheduleRepository, val context: Context) : BaseViewModel() {

    fun startSchedule(calendar: Calendar,appName: String,packageName: String) {
        val timestamp: Long = calendar.timeInMillis
        viewModelScope.launch(Dispatchers.IO){
            val jobTag  = generateKey()
            scheduleRepository.createSchedule(ScheduleEntity(0,timestamp,appName,packageName,false,
                jobTag)
            )
            AppScheduleWorker.createSchedule(System.currentTimeMillis()-timestamp,saveData(jobTag,appName,packageName),jobTag)
        }

    }
    fun updateSchedule(calendar: Calendar,appName: String,packageName: String,id: Int,tag: String) {
        val timestamp: Long = calendar.timeInMillis
        deleteTaskFromQue(tag)
        viewModelScope.launch(Dispatchers.IO){
            val jobTag  = generateKey()
            scheduleRepository.updateSchedule(ScheduleEntity(id,timestamp,appName,packageName,false, jobTag))
            AppScheduleWorker.createSchedule(System.currentTimeMillis()-timestamp,saveData(jobTag,appName,packageName),jobTag)
        }

    }
    private fun deleteTaskFromQue(tag: String) {
        WorkManager.getInstance(context).cancelAllWorkByTag(tag)
    }
    private fun generateKey(): String {
        return UUID.randomUUID().toString()
    }
    private fun saveData(uuid: String,appName: String, packageName: String): Data {
        return Data.Builder()
            .putString(AppConstants.UUID, uuid)
            .putString(AppConstants.PACKAGE_NAME, packageName)
            .putString(AppConstants.APP_NAME, appName)
            .build()
    }
}