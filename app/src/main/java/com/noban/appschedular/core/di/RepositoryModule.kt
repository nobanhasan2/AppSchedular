package com.noban.appschedular.core.di

import com.noban.appschedular.data.repository.ScheduleRepository
import com.noban.appschedular.data.repository.ScheduleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideScheduleRepository(scheduleRepositoryImpl: ScheduleRepositoryImpl): ScheduleRepository
}