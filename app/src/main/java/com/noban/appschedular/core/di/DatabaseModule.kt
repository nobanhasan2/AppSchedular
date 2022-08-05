package com.noban.appschedular.core.di

import android.content.Context
import androidx.room.Room
import com.noban.appschedular.data.AppDatabase
import com.noban.appschedular.util.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        AppConstants.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideLoginDao(db: AppDatabase) = db.scheduleDao()


}