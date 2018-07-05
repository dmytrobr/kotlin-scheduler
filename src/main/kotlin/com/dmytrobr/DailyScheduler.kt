package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*


fun dailySchedule(schedule: Schedule): List<LocalDate> = getDatesFromRangeWithCondition(schedule.startDate, schedule.endDate) {
    date: LocalDate ->
    !schedule.businessDay ||
            schedule.businessDay && !isWeekend(date)
}


fun isWeekend(date: LocalDate) =
        EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(date.dayOfWeek)


