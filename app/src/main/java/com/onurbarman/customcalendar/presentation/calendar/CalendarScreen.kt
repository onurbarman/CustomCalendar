package com.onurbarman.customcalendar.presentation.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.onurbarman.customcalendar.R
import com.onurbarman.customcalendar.domain.model.schedule.ScheduleItem
import com.onurbarman.customcalendar.presentation.calendar.model.CalendarUiState
import com.onurbarman.customcalendar.utils.CalendarAlpha.alpha02
import com.onurbarman.customcalendar.utils.CalendarHeights.height16
import com.onurbarman.customcalendar.utils.CalendarHeights.height2
import com.onurbarman.customcalendar.utils.CalendarHeights.height8
import com.onurbarman.customcalendar.utils.CalendarPaddings.padding16
import com.onurbarman.customcalendar.utils.CalendarPaddings.padding32
import com.onurbarman.customcalendar.utils.CalendarPaddings.padding8
import com.onurbarman.customcalendar.utils.CalendarTextField.descriptionMaxLines
import com.onurbarman.customcalendar.utils.CalendarTextField.descriptionMinLines
import com.onurbarman.customcalendar.utils.CalendarTextField.titleMaxLines
import com.onurbarman.customcalendar.utils.dateToLocalDateFormat
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel()
) {

    val calendarState by viewModel.getCalendarStateFlow().collectAsState()

    val calendarUiState by viewModel.getCalendarUiStateFlow().collectAsState()

    LaunchedEffect(calendarUiState.selectedDay) {
        viewModel.getAllScheduleItems(calendarUiState.selectedDay.toString())
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.add_event)
                    )
                },
                onClick = {
                    viewModel.updateShowBottomSheet(showBottomSheet = true)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { _ ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CalendarContent(
                calendarUiState = calendarUiState,
                onDayClicked = { day ->
                    calendarState.updateSelectedDay(day)
                },
                onPreviousMonthClicked = {
                    calendarState.getPreviousMonth()
                },
                onNextMonthClicked = {
                    calendarState.getNextMonth()
                },
                selectedDay = calendarUiState.selectedDay
            )
            calendarUiState.scheduleItems?.let { scheduleItems ->
                Spacer(
                    modifier = Modifier
                        .padding(top = padding16)
                )
                CalendarScheduleTitle(calendarUiState = calendarUiState)
                Spacer(modifier = Modifier.height(height16))
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = padding16)
                ) {
                    items(scheduleItems.size) { index ->
                        CalendarScheduleItem(scheduleItems[index])
                    }
                }
            }

        }

        //Bottom Sheet
        if (calendarUiState.showBottomSheet) {
            CalendarBottomSheet(
                calendarUiState = calendarUiState,
                updateShowBottomSheet = {
                    viewModel.updateShowBottomSheet(it)
                },
                scheduleNewItem = {
                    viewModel.insertScheduleItem(it)
                })
        }
    }
}

@Composable
fun CalendarScheduleTitle(
    calendarUiState: CalendarUiState
) {
    val date = dateToLocalDateFormat(calendarUiState.selectedDay)
    Text(
        modifier = Modifier
            .padding(horizontal = padding16),
        text = date.toString() + stringResource(R.string.events_addition),
        style = MaterialTheme.typography.titleLarge.copy(
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    )
}
@Composable
fun CalendarScheduleItem(scheduleItem: ScheduleItem) {
    Column {
        Text(
            text = scheduleItem.title,
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(height2))
        scheduleItem.description?.let { description ->
            Text(
                text = description,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.W400
                )
            )
        }
        Spacer(modifier = Modifier.height(height8))
        Divider(
            modifier = Modifier.alpha(alpha02)
        )
        Spacer(modifier = Modifier.height(height8))
    }
}

@Composable
private fun CalendarContent(
    calendarUiState: CalendarUiState,
    onDayClicked: (LocalDate) -> Unit,
    onPreviousMonthClicked: () -> Unit,
    onNextMonthClicked: () -> Unit,
    selectedDay: LocalDate? = null
) {
    Calendar(
        calendarUiState = calendarUiState,
        onDayClicked = onDayClicked,
        onPreviousMonthClicked = onPreviousMonthClicked,
        onNextMonthClicked = onNextMonthClicked,
        selectedDay = selectedDay
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarBottomSheet(
    calendarUiState: CalendarUiState,
    updateShowBottomSheet: (Boolean) -> Unit,
    scheduleNewItem: (ScheduleItem) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var titleText by remember { mutableStateOf("") }
    var descriptionText by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = {
            updateShowBottomSheet(false)
        },
        sheetState = sheetState,
    ) {
        // Sheet content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding32)
        ) {
            val date = dateToLocalDateFormat(calendarUiState.selectedDay)
            Text(
                text = date.toString(),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.Black
                )
            )
            Spacer(
                modifier = Modifier
                    .padding(top = padding16)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = titleText,
                onValueChange = {
                    titleText = it
                },
                label = {
                    Text(text = stringResource(R.string.title))
                },
                maxLines = titleMaxLines
            )
            Spacer(
                modifier = Modifier
                    .padding(top = padding8)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = descriptionText,
                onValueChange = {
                    descriptionText = it
                },
                label = {
                    Text(text = stringResource(R.string.description))
                },
                minLines = descriptionMinLines,
                maxLines = descriptionMaxLines
            )
            Spacer(
                modifier = Modifier
                    .padding(top = padding16)
            )
            Button(
                onClick = {
                    scheduleNewItem(
                        ScheduleItem(
                            title = titleText,
                            description = descriptionText,
                            date = calendarUiState.selectedDay.toString()
                        )
                    )
                    scope.launch {
                        updateShowBottomSheet(false)
                        sheetState.hide()
                    }
                }) {
                Text(
                    text = stringResource(id = R.string.add_event),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White
                    )
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(top = 24.dp)
            )
        }
    }
}

@Preview
@Composable
fun CalendarScheduleItemPreview() {
    CalendarScheduleItem(
        scheduleItem = ScheduleItem(
            title = "Demo Title",
            description = "Demo Description with long text",
            date = "02.17.2024"
        )
    )
}
