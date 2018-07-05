package com.dmytrobr

import java.time.LocalDate
import java.util.*


fun findAllNDaysInPeriod(
        dayPosition: Int,
        startDate: LocalDate,
        endDate: LocalDate,
        shiftToNextPeriod: (LocalDate) -> LocalDate,
        findNextDate: (LocalDate, Int) -> LocalDate): ArrayList<LocalDate> {
    val result = ArrayList<LocalDate>()
    var nextDate = findNextDate(startDate, dayPosition)
    while (nextDate.isBefore(endDate)) {
        if (!nextDate.isBefore(startDate)) result.add(nextDate)
        nextDate = findNextDate(shiftToNextPeriod(nextDate), dayPosition)
    }
    return result

}


fun getDatesFromRangeWithCondition(startDate: LocalDate, endDate: LocalDate, condition: (LocalDate) -> Boolean): List<LocalDate> {
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
