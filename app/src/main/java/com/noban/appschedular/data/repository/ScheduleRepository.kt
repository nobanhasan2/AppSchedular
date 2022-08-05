package com.noban.appschedular.data.repository

import com.noban.appschedular.data.model.ScheduleEntity

interface ScheduleRepository {
    fun createSchedule(scheduleEntity : ScheduleEntity)
    fun updateSchedule(scheduleEntity : ScheduleEntity) : Int
    fun deleteSchedule(scheduleEntity : ScheduleEntity) : Int
    fun getAllSchedule() : List<ScheduleEntity>?
    fun completeSchedule(uuid :String) : Int
}