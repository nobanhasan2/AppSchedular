package com.noban.appschedular.features.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.WorkManager
import com.noban.appschedular.core.platform.BaseViewModel
import com.noban.appschedular.data.dao.ScheduleDao
import com.noban.appschedular.data.model.ScheduleEntity
import com.noban.appschedular.data.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val scheduleRepository: ScheduleRepository,val context : Context) : BaseViewModel()  {
    val scheduleList : MutableLiveData<List<ScheduleEntity>> = MutableLiveData()

    fun getAllSchedules(){
        viewModelScope.launch(Dispatchers.IO) {
           scheduleList.postValue(scheduleRepository.getAllSchedule())
        }
    }

    fun updateSchedule(hourOfDay: Int, minute: Int, item: ScheduleEntity) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        val timestamp: Long = calendar.timeInMillis
        viewModelScope.launch(Dispatchers.IO){
            scheduleRepository.updateSchedule(ScheduleEntity(item.id,timestamp,item.scheduleName,item.packageName,false,
                item.jobTag))
        }
    }
    fun deleteSchedule(schedule: ScheduleEntity){
        viewModelScope.launch(Dispatchers.IO){
            scheduleRepository.deleteSchedule(schedule)
            deleteTaskFromQue(schedule.jobTag)
        }
    }
    private fun deleteTaskFromQue(tag: String) {
        WorkManager.getInstance(context).cancelAllWorkByTag(tag)
    }


}