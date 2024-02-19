package com.onurbarman.customcalendar.domain.model.calendar

import java.time.YearMonth

data class Month(
    val yearMonth: YearMonth,
    val weeks: List<Week>
)
