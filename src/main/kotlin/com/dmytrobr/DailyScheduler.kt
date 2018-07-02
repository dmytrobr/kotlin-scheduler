package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*


fun getDatesFromRange(startDate: LocalDate, endDate: LocalDate, condition: (LocalDate) -> Boolean): List<LocalDate> {
    val runDays = ArrayList<LocalDate>()
    var currentDay = startDate
    do {
        if (condition(currentDay)) {
            runDays.add(currentDay)
        }
        currentDay = currentDay.plusDays(1)
    } while (currentDay.isBefore(endDate))
    return runDays
}

fun dailySchedule(schedule: Schedule): List<LocalDate> {
    return getDatesFromRange(schedule.startDate, schedule.endDate)
    { date: LocalDate ->
        schedule.businessDay && isWeekend(date)
    }
}

fun isWeekend(date: LocalDate) =
        EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(date.dayOfWeek)


