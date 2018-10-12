package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*


fun iterateWithCondition(schedule: Schedule, ifCondition: (LocalDate) -> Boolean): List<LocalDate> {
    val result = LinkedList<LocalDate>()
    for (date in schedule.startDate..schedule.endDate.minusDays(1) condition ifCondition) {
        result.add(date)
    }
    return result
}
fun isWeekend(date: LocalDate) =
        EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(date.dayOfWeek)

