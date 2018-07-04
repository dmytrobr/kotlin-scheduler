package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*


fun getDatesFromRange(startDate: LocalDate, endDate: LocalDate, condition: (LocalDate) -> Boolean): List<LocalDate> {
    val runDays = ArrayList<LocalDate>()
    var currentDay = startDate
    do {
        val condition1 = condition(currentDay)
        if (condition1) {
            runDays.add(currentDay)
        }
        currentDay = currentDay.plusDays(1)
    } while (currentDay.isBefore(endDate))
    return runDays
}

fun dailySchedule(schedule: Schedule): List<LocalDate> = getDatesFromRange(schedule.startDate, schedule.endDate) { date: LocalDate ->
    !schedule.businessDay ||
            schedule.businessDay && !isWeekend(date)
}

//fun dailySchedule(schedule: Schedule): List<LocalDate> {
//    val runDays = ArrayList<LocalDate>()
//    var currentDay = schedule.startDate
//    do {
//        if (!schedule.businessDay ||
//                schedule.businessDay && !isWeekend(currentDay)) {
//            runDays.add(currentDay)
//        }
//        currentDay = currentDay.plusDays(1)
//    } while (currentDay.isBefore(schedule.endDate))
//    return runDays
//}


fun isWeekend(date: LocalDate) =
        EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(date.dayOfWeek)


