package com.onurbarman.customcalendar.presentation.calendar.model

import com.onurbarman.customcalendar.domain.model.calendar.Month
import com.onurbarman.customcalendar.domain.model.calendar.Week
import com.onurbarman.customcalendar.utils.getNumberWeeks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.Period
import java.time.YearMonth

class CalendarState(
    val calendarUiState: MutableStateFlow<CalendarUiState>
) {

    companion object {
        const val DAYS_IN_WEEK = 7
    }

    val listMonths: List<Month>

    private val calendarStartDate: LocalDate = LocalDate.now()
        .withMonth(1).withDayOfMonth(1)

    // Defaulting to 2 years from current date.
    private val calendarEndDate: LocalDate = LocalDate.now().plusYears(2)
        .withMonth(12).withDayOfMonth(31)

    private val periodBetweenCalendarStartEnd: Period = Period.between(
        calendarStartDate,
        calendarEndDate
    )

    //New
    private var calendarDate: LocalDate = LocalDate.now()

    init {
        val tempListMonths = mutableListOf<Month>()
        var startYearMonth = YearMonth.from(calendarStartDate)
        for (numberMonth in 0..periodBetweenCalendarStartEnd.toTotalMonths()) {
            val numberWeeks = startYearMonth.getNumberWeeks()
            val listWeekItems = mutableListOf<Week>()
            for (week in 0 until numberWeeks) {
                listWeekItems.add(
                    Week(
                        number = week,
                        yearMonth = startYearMonth
                    )
                )
            }
            val month = Month(startYearMonth, listWeekItems)
            tempListMonths.add(month)
            startYearMonth = startYearMonth.plusMonths(1)
        }
        listMonths = tempListMonths.toList()

        //new
        calendarUiState.update {
            it.copy(
                selectedDay = LocalDate.now()
            )
        }
        updateCalendar()
    }

    fun getNextMonth() {
        calendarDate = calendarDate.plusMonths(1)
        updateCalendar()
    }

    fun getPreviousMonth() {
        calendarDate = calendarDate.minusMonths(1)
        updateCalendar()
    }

    fun updateSelectedDay(day: LocalDate) {
        calendarUiState.update {
            it.copy(
                selectedDay = day
            )
        }
    }

    private fun updateCalendar() {
        val newYearMonth = YearMonth.from(calendarDate)
        val numberWeeks = newYearMonth.getNumberWeeks()
        val listWeekItems = mutableListOf<Week>()
        for (week in 0 until numberWeeks) {
            listWeekItems.add(
                Week(
                    number = week,
                    yearMonth = newYearMonth
                )
            )
        }
        calendarUiState.update {
            it.copy(
                month = Month(newYearMonth, listWeekItems)
            )
        }
    }
}