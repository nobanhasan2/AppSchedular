package com.noban.appschedular.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.noban.appschedular.data.dao.ScheduleDao
import com.noban.appschedular.data.model.ScheduleEntity
import com.noban.appschedular.util.AppConstants

@Database(entities =   [ScheduleEntity::class,
                       ], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DATABASE_NAME)
                .build()
    }

}