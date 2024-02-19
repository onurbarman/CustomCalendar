package com.onurbarman.customcalendar.presentation.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.onurbarman.customcalendar.presentation.calendar.model.CalendarUiState
import com.onurbarman.customcalendar.domain.model.calendar.Month
import com.onurbarman.customcalendar.presentation.calendar.sub_views.DaysOfWeek
import com.onurbarman.customcalendar.presentation.calendar.sub_views.MonthHeader
import com.onurbarman.customcalendar.presentation.calendar.sub_views.WeekView
import com.onurbarman.customcalendar.utils.CalendarHeights.height8
import com.onurbarman.customcalendar.utils.CalendarPaddings.padding12
import com.onurbarman.customcalendar.utils.CalendarPaddings.padding16
import java.time.LocalDate

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarUiState: CalendarUiState,
    onDayClicked: (LocalDate) -> Unit,
    onPreviousMonthClicked: () -> Unit,
    onNextMonthClicked: () -> Unit,
    selectedDay: LocalDate? = null
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        calendarUiState.month?.let { currentMonth ->
            itemsCalendarMonth(
                onDayClicked,
                currentMonth,
                onPreviousMonthClicked,
                onNextMonthClicked,
                selectedDay
            )
        }
    }
}

private fun LazyListScope.itemsCalendarMonth(
    onDayClicked: (LocalDate) -> Unit,
    month: Month,
    onPreviousMonthClicked: () -> Unit,
    onNextMonthClicked: () -> Unit,
    selectedDay: LocalDate? = null
) {
    item("monthSelector") {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding16, end = padding12),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPreviousMonthClicked) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    tint = Color.White,
                    contentDescription = "Previous",
                )
            }
            IconButton(onClick = onNextMonthClicked) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    tint = Color.White,
                    contentDescription = "Next"
                )
            }
        }
    }
    
    item(month.yearMonth.month.name + month.yearMonth.year + "header") {
        MonthHeader(
            monthName = month.yearMonth.month.name,
            year = month.yearMonth.year.toString()
        )
    }

    item(month.yearMonth.month.name + month.yearMonth.year + "daysOfWeek") {
        DaysOfWeek()
    }

    // A custom key needs to be given to these items so that they can be found in tests that
    // need scrolling. The format of the key is ${year/month/weekNumber}. Thus,
    // the key for the fourth week of December 2020 is "2020/12/4"
    itemsIndexed(month.weeks, key = { index, _ ->
        month.yearMonth.year.toString() +
                "/" +
                month.yearMonth.month.value +
                "/" +
                (index + 1).toString()
    }) { _, week ->
        WeekView(
            week = week,
            onDayClicked = onDayClicked,
            selectedDay = selectedDay
        )
        Spacer(Modifier.height(height8))
    }
}