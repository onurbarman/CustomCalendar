package com.onurbarman.customcalendar.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun dateToLocalDateFormat(day: LocalDate?): String? {
    return day?.format(
        DateTimeFormatter.ofLocalizedDate(
            FormatStyle.LONG
        )
    )
}