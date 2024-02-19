package com.onurbarman.customcalendar.data.repository

import com.onurbarman.customcalendar.data.local.database.dao.CalendarScheduleDao
import com.onurbarman.customcalendar.data.local.database.entities.CalendarScheduleItemEntity
import com.onurbarman.customcalendar.data.local.database.entities.toScheduleItemEntity
import com.onurbarman.customcalendar.domain.model.schedule.ScheduleItem
import com.onurbarman.customcalendar.domain.repository.CalendarScheduleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalendarScheduleRepositoryImpl @Inject constructor(
    private val calendarScheduleDao: CalendarScheduleDao,
): CalendarScheduleRepository {

    override suspend fun upsertScheduleItem(item: ScheduleItem) {
        calendarScheduleDao.upsertScheduleItem(item.toScheduleItemEntity())
    }

    override suspend fun getAllScheduleItemsForDate(date: String): List<CalendarScheduleItemEntity>? {
        return calendarScheduleDao.getAllScheduleItemsForDate(date)
    }

    override suspend fun getScheduleItem(id: Long): CalendarScheduleItemEntity? {
        return calendarScheduleDao.getScheduleItem(id)
    }

    override suspend fun clearAll() {
        calendarScheduleDao.clearAll()
    }

    override suspend fun deleteScheduleItem(id: Long) {
        calendarScheduleDao.deleteScheduleItem(id)
    }
}