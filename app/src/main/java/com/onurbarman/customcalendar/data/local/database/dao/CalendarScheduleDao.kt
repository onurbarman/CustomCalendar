package com.onurbarman.customcalendar.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.onurbarman.customcalendar.data.local.database.entities.CalendarScheduleItemEntity

@Dao
interface CalendarScheduleDao {

    @Upsert
    suspend fun upsertScheduleItem(item: CalendarScheduleItemEntity)

    @Query("SELECT * FROM schedule_items WHERE date = :date")
    suspend fun getAllScheduleItemsForDate(date: String): List<CalendarScheduleItemEntity>?

    @Query("SELECT * FROM schedule_items WHERE id = :id")
    suspend fun getScheduleItem(id: Long): CalendarScheduleItemEntity?

    @Query("DELETE FROM schedule_items")
    suspend fun clearAll()

    @Query("DELETE FROM schedule_items WHERE id = :id")
    suspend fun deleteScheduleItem(id: Long)
}