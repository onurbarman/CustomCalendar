package com.onurbarman.customcalendar.domain.repository

import com.onurbarman.customcalendar.data.local.database.entities.CalendarScheduleItemEntity
import com.onurbarman.customcalendar.domain.model.schedule.ScheduleItem

interface CalendarScheduleRepository {

    suspend fun upsertScheduleItem(item: ScheduleItem)

    suspend fun getAllScheduleItemsForDate(date: String): List<CalendarScheduleItemEntity>?

    suspend fun getScheduleItem(id: Long): CalendarScheduleItemEntity?

    suspend fun clearAll()

    suspend fun deleteScheduleItem(id: Long)
}