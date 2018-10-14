package com.dmytrobr

import java.time.LocalDate
import java.time.temporal.TemporalAdjuster


fun findNBusinessDayInPeriod(adjustToFirstDayOfPeriod: TemporalAdjuster, adjustToLastDay: TemporalAdjuster, n: Int): (LocalDate) -> LocalDate = { currentDay ->
    var dayCounter = 1L
    var dayCounterShift = 1L
    var startDate = currentDay.with(adjustToFirstDayOfPeriod)
    if (n < 0) {
        dayCounter = -1
        dayCounterShift = -1
        startDate = startDate.with(adjustToLastDay)
    }
    var iterated = findNextOrSameBusinessDay(startDate, dayCounterShift)
    while (!dayCounter.equals(n.toLong())) {
        iterated = findNextOrSameBusinessDay(iterated.plusDays(dayCounterShift), dayCounterShift)
        dayCounter = dayCounter.plus(dayCounterShift)
    }
    iterated
}

fun findNextOrSameBusinessDay(current: LocalDate, dayCounterShift: Long): LocalDate {
    var result = current
    while (isWeekend(result)) {
        result = result.plusDays(dayCounterShift)
    }
    return result
}
