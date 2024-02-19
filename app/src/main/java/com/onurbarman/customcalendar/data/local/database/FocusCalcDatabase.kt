package com.onurbarman.customcalendar.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onurbarman.customcalendar.data.local.database.dao.CalendarScheduleDao
import com.onurbarman.customcalendar.data.local.database.entities.CalendarScheduleItemEntity

@Database(
    entities = [CalendarScheduleItemEntity::class],
    version = 1,
    exportSchema = true
)
abstract class CustomCalendarDatabase : RoomDatabase() {

    abstract val scheduleItemsDao: CalendarScheduleDao

    companion object {

        const val CUSTOM_CALENDAR_DATABASE_NAME: String = "custom_calendar_database"
    }
}