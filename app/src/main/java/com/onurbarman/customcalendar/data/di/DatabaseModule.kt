package com.onurbarman.customcalendar.data.di

import android.content.Context
import androidx.room.Room
import com.onurbarman.customcalendar.data.local.database.CustomCalendarDatabase
import com.onurbarman.customcalendar.data.local.database.CustomCalendarDatabase.Companion.CUSTOM_CALENDAR_DATABASE_NAME
import com.onurbarman.customcalendar.data.local.database.dao.CalendarScheduleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCustomCalendarDatabase(
        @ApplicationContext context: Context
    ): CustomCalendarDatabase = Room
        .databaseBuilder(
            context,
            CustomCalendarDatabase::class.java,
            CUSTOM_CALENDAR_DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideScheduleItemsDao(
        database: CustomCalendarDatabase
    ): CalendarScheduleDao = database.scheduleItemsDao
}