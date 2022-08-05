package com.noban.appschedular.data.repository

import com.noban.appschedular.data.dao.ScheduleDao
import com.noban.appschedular.data.model.ScheduleEntity
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(val dao: ScheduleDao) : ScheduleRepository {
    override fun createSchedule(scheduleEntity: ScheduleEntity) = dao.insertSchedule(scheduleEntity)

    override fun updateSchedule(scheduleEntity: ScheduleEntity) = dao.updateSchedule(scheduleEntity)

    override fun deleteSchedule(scheduleEntity: ScheduleEntity) = dao.deleteSchedule(scheduleEntity)

    override fun getAllSchedule(): List<ScheduleEntity>? = dao.getAllSchedule()

    override fun completeSchedule(uuid: String) =  dao.updateComplete(uuid)
}