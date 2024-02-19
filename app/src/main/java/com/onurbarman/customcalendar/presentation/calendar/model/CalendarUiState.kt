package com.onurbarman.customcalendar.presentation.calendar.model

import com.onurbarman.customcalendar.domain.model.calendar.Month
import com.onurbarman.customcalendar.domain.model.schedule.ScheduleItem
import java.time.LocalDate

data class CalendarUiState(
    val month: Month? = null,
    val selectedDay: LocalDate? = null,
    val scheduleItems: List<ScheduleItem>? = null,
    val showBottomSheet: Boolean = false
)