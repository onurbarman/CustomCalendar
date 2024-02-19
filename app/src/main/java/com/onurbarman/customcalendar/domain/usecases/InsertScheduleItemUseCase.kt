package com.onurbarman.customcalendar.domain.usecases

import com.onurbarman.customcalendar.domain.model.schedule.ScheduleItem
import com.onurbarman.customcalendar.domain.repository.CalendarScheduleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertScheduleItemUseCase @Inject constructor(
    private val repository: CalendarScheduleRepository
) {

    suspend operator fun invoke(item: ScheduleItem) {
        repository.upsertScheduleItem(item)
    }
}