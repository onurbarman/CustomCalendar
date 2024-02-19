package com.onurbarman.customcalendar.domain.usecases

import com.onurbarman.customcalendar.data.local.database.entities.toScheduleItemsDomain
import com.onurbarman.customcalendar.domain.model.schedule.ScheduleItem
import com.onurbarman.customcalendar.domain.repository.CalendarScheduleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllScheduleItemsUseCase @Inject constructor(
    private val repository: CalendarScheduleRepository
) {

    suspend operator fun invoke(date: String): List<ScheduleItem>? {
       return repository.getAllScheduleItemsForDate(date)?.toScheduleItemsDomain()
    }
}