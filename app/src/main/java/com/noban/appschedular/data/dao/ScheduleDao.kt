package com.noban.appschedular.data.dao

import androidx.room.*
import com.noban.appschedular.data.model.ScheduleEntity

@Dao
interface ScheduleDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertSchedule(vararg schedule: ScheduleEntity)

     @Update(onConflict = OnConflictStrategy.REPLACE)
     fun updateSchedule(scheduleEntity: ScheduleEntity): Int

     @Delete
     fun deleteSchedule(scheduleEntity: ScheduleEntity) : Int

     @Query("SELECT * FROM SCHEDULE_TABLE")
     fun getAllSchedule(): List<ScheduleEntity>?

     @Query("Update SCHEDULE_TABLE set isCompleted = 1 where jobTag = :uuid")
     fun updateComplete(uuid : String): Int
}