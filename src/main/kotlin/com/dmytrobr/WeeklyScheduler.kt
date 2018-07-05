package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.LocalDate

fun weeklySchedule(schedule: Schedule): List<LocalDate> {
    return getDatesFromRangeWithCondition(schedule.startDate,schedule.endDate){ date: LocalDate ->
        schedule.dayPositions.contains(date.dayOfWeek.value)
    }
}


