package com.onurbarman.customcalendar.utils

import java.time.YearMonth
import java.time.temporal.WeekFields

internal val CALENDAR_STARTS_ON = WeekFields.ISO

fun YearMonth.getNumberWeeks(weekFields: WeekFields = CALENDAR_STARTS_ON): Int {
    val firstWeekNumber = this.atDay(1)[weekFields.weekOfMonth()]
    val lastWeekNumber = this.atEndOfMonth()[weekFields.weekOfMonth()]
    return lastWeekNumber - firstWeekNumber + 1 // Both weeks inclusive
}
