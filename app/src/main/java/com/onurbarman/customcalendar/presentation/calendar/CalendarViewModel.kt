package com.onurbarman.customcalendar.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onurbarman.customcalendar.domain.model.schedule.ScheduleItem
import com.onurbarman.customcalendar.domain.usecases.ClearAllScheduleItemsUseCase
import com.onurbarman.customcalendar.domain.usecases.DeleteScheduleItemUseCase
import com.onurbarman.customcalendar.domain.usecases.GetAllScheduleItemsUseCase
import com.onurbarman.customcalendar.domain.usecases.GetScheduleItemUseCase
import com.onurbarman.customcalendar.domain.usecases.InsertScheduleItemUseCase
import com.onurbarman.customcalendar.presentation.calendar.model.CalendarState
import com.onurbarman.customcalendar.presentation.calendar.model.CalendarUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DELAY_TIME: Long = 200
@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getAllScheduleItemsUseCase: GetAllScheduleItemsUseCase,
    private val getScheduleItemUseCase: GetScheduleItemUseCase,
    private val insertScheduleItemUseCase: InsertScheduleItemUseCase,
    private val deleteScheduleItemUseCase: DeleteScheduleItemUseCase,
    private val clearAllScheduleItemsUseCase: ClearAllScheduleItemsUseCase
): ViewModel() {

    private val calendarUiState: MutableStateFlow<CalendarUiState> = MutableStateFlow(
        CalendarUiState()
    )

    private val calendarState: MutableStateFlow<CalendarState> = MutableStateFlow(
        CalendarState(calendarUiState)
    )

    fun getCalendarStateFlow() = calendarState.asStateFlow()

    fun getCalendarUiStateFlow() = calendarUiState.asStateFlow()

    fun getAllScheduleItems(date: String) {
        viewModelScope.launch {
            val scheduleItems = getAllScheduleItemsUseCase.invoke(date)
            calendarUiState.update {
                it.copy(
                    scheduleItems = scheduleItems
                )
            }
        }
    }

    fun insertScheduleItem(item: ScheduleItem) {
        viewModelScope.launch {
            insertScheduleItemUseCase.invoke(item)
            delay(DELAY_TIME)
            getAllScheduleItems(item.date)
        }
    }

    fun updateShowBottomSheet(showBottomSheet: Boolean) {
        calendarUiState.update {
            it.copy(
                showBottomSheet = showBottomSheet
            )
        }
    }
}