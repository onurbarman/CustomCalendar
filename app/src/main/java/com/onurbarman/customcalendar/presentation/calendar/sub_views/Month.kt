package com.onurbarman.customcalendar.presentation.calendar.sub_views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.onurbarman.customcalendar.ui.theme.Purple800
import com.onurbarman.customcalendar.utils.CalendarAlpha.alpha08
import com.onurbarman.customcalendar.utils.CalendarPaddings.padding16
import com.onurbarman.customcalendar.utils.CalendarPaddings.padding32
import com.onurbarman.customcalendar.utils.CalendarPaddings.padding8

@Composable
fun MonthHeader(
    modifier: Modifier = Modifier,
    monthName: String,
    year: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = padding8, start = padding32, end = padding32),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = monthName,
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.W300

            )
        )
        Text(
            text = year,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color.White.copy(
                    alpha = alpha08
                ),

            )
        )
    }
}

@Composable
fun MonthView(
    modifier: Modifier = Modifier,
    monthName: String,
    year: String
) {
    Column(
        modifier = modifier
            .padding(padding16),
        horizontalAlignment = Alignment.Start
    ) {
        MonthHeader(
            monthName = monthName,
            year = year
        )
        Spacer(
            Modifier.height(
                padding16
            )
        )
        DaysOfWeek()
    }
}

@Preview
@Composable
private fun MonthPreview() {
    MonthView(
        modifier = Modifier
            .background(Purple800),
        monthName = "January",
        year = "2024"
    )
}