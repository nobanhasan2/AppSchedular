package com.noban.appschedular.data.model


import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "schedule_table")
data class ScheduleEntity (
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var scheduleTime : Long,
    var scheduleName : String,
    var packageName : String,
    val isCompleted : Boolean,
    var jobTag : String)
