package com.onurbarman.customcalendar.domain.model.schedule

data class ScheduleItem(
    val id: Long = 0,
    val title: String = "",
    val description: String? = null,
    val date: String
)