package com.onurbarman.customcalendar.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onurbarman.customcalendar.domain.model.schedule.ScheduleItem

@Entity(tableName = "schedule_items")
data class CalendarScheduleItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String = "",
    val description: String? = null,
    val date: String
)

fun CalendarScheduleItemEntity.toScheduleItemDomain(): ScheduleItem = ScheduleItem(
    id,
    title,
    description,
    date
)

fun List<CalendarScheduleItemEntity>.toScheduleItemsDomain(): List<ScheduleItem> =
    this.map { historyItemEntity ->
        historyItemEntity.toScheduleItemDomain()
    }

fun ScheduleItem.toScheduleItemEntity(): CalendarScheduleItemEntity = CalendarScheduleItemEntity(
    id,
    title,
    description,
    date
)