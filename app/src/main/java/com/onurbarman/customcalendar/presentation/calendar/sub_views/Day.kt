package com.onurbarman.customcalendar.presentation.calendar.sub_views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onurbarman.customcalendar.ui.theme.Purple800
import com.onurbarman.customcalendar.utils.CalendarAlpha.alpha05
import com.onurbarman.customcalendar.utils.CalendarConstants.CELL_SIZE
import com.onurbarman.customcalendar.utils.CalendarPaddings.padding8
import java.time.LocalDate

@Composable
fun DayNameHeader(
    modifier: Modifier = Modifier,
    day: String
) {
    Box(
        modifier = modifier
            .size(CELL_SIZE)
            .padding(
                vertical = padding8
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color.White.copy(
                    alpha = alpha05
                )
            )
        )
    }
}

@Composable
fun DayContent(
    modifier: Modifier = Modifier,
    day: LocalDate,
    onDayClicked: (LocalDate) -> Unit,
    selectedDay: LocalDate? = null
) {
    DayView(
        modifier = modifier,
        day = day,
        onDayClicked,
        selectedDay = selectedDay
    )
}

@Composable
fun DayView(
    modifier: Modifier = Modifier,
    day: LocalDate,
    onDayClicked: (LocalDate) -> Unit,
    selectedDay: LocalDate? = null
) {
    val isToday = day == LocalDate.now()
    val isSelected = day == selectedDay
    Box(
        modifier = modifier
            .size(CELL_SIZE)
            .clickable {
                onDayClicked(day)
            }
            .padding(padding8)
            .background(
                color = if (isSelected) {
                    MaterialTheme.colorScheme.outlineVariant
                } else if (isToday) {
                    Color.White
                } else {
                    Color.Transparent
                },
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = if (isToday || isSelected) {
                    Color.Black
                } else {
                    Color.White
                }
            )
        )
    }
}

@Preview
@Composable
fun DayViewPreview(
) {
    DayView(
        day = LocalDate.now(),
        modifier = Modifier
            .background(Purple800),
        onDayClicked = {},
        selectedDay = LocalDate.now()
    )
}