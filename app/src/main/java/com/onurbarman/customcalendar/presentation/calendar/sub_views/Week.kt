package com.onurbarman.customcalendar.presentation.calendar.sub_views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onurbarman.customcalendar.domain.model.calendar.Week
import com.onurbarman.customcalendar.ui.theme.Purple800
import com.onurbarman.customcalendar.utils.CalendarAlpha.alpha01
import com.onurbarman.customcalendar.utils.CalendarAlpha.alpha04
import com.onurbarman.customcalendar.utils.CalendarConstants.CELL_SIZE
import com.onurbarman.customcalendar.utils.CalendarPaddings.padding24
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Composable
internal fun DaysOfWeek(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = modifier) {
            for (day in DayOfWeek.values()) {
                DayNameHeader(
                    day = day.name.take(1)
                )
            }
        }
        Divider(
            modifier = Modifier
                .padding(
                    horizontal = padding24
                ),
            thickness = 1.dp,
            color = Color.White.copy(
                alpha = alpha04
            ),
        )
    }

}

@Composable
fun WeekView(
    modifier: Modifier = Modifier,
    onDayClicked: (LocalDate) -> Unit,
    week: Week,
    selectedDay: LocalDate? = null
) {
    val beginningWeek = week.yearMonth.atDay(1).plusWeeks(week.number.toLong())
    var currentDay = beginningWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

    Column {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in 0..6) {
                if (currentDay.month == week.yearMonth.month) {
                    DayContent(
                        day = currentDay,
                        onDayClicked = onDayClicked,
                        selectedDay = selectedDay
                    )
                } else {
                    Box(modifier = Modifier.size(CELL_SIZE))
                }
                currentDay = currentDay.plusDays(1)
            }
        }
        Divider(
            modifier = Modifier
                .padding(
                    horizontal = padding24
                ),
            thickness = 1.dp,
            color = Color.White.copy(
                alpha = alpha01
            ),
        )
    }

}

@Preview
@Composable
private fun DaysOfWeekPreview() {
    DaysOfWeek(
        modifier = Modifier
            .background(Purple800)
    )
}