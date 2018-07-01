package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

fun weeklySchedule(schedule: Schedule): List<LocalDate> {
    return getDatesFromRange(schedule.startDate,schedule.endDate){date: LocalDate ->
        schedule.dayPositions.contains(date.dayOfWeek.value)
    }
}


