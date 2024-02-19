package com.onurbarman.customcalendar.data.di

import com.onurbarman.customcalendar.data.repository.CalendarScheduleRepositoryImpl
import com.onurbarman.customcalendar.domain.repository.CalendarScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindCalendarScheduleRepository(repository: CalendarScheduleRepositoryImpl): CalendarScheduleRepository

}