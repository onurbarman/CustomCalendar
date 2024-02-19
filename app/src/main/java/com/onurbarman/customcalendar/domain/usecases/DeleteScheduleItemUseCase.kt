package com.onurbarman.customcalendar.domain.usecases

import com.onurbarman.customcalendar.domain.repository.CalendarScheduleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteScheduleItemUseCase @Inject constructor(
    private val repository: CalendarScheduleRepository
) {

    suspend operator fun invoke(id: Long) {
        repository.deleteScheduleItem(id)
    }
}