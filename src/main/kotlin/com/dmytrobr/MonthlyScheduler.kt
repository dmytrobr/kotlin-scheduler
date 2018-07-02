package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.util.*

fun monthlySchedule(schedule: Schedule): List<LocalDate> {
    val findNextNDayInMonth: (LocalDate, Int) -> LocalDate = if (schedule.businessDay) {
        findNDayInMonth()
    } else {
        findNBusinessDayInPeriod(TemporalAdjusters.firstDayOfMonth(), TemporalAdjusters.lastDayOfMonth())
    }
    val dates = ArrayList<LocalDate>()
    for (n in schedule.dayPositions) {
        DayPositionIterator(n, schedule.startDate, schedule.endDate, jumpToNextMonth(), findNextNDayInMonth).forEach { date ->
            dates.add(date)
        }

    }
    return dates
}

fun findNBusinessDayInPeriod(adjustToFirstDayOfPeriod: TemporalAdjuster, adjustToLastDay: TemporalAdjuster): (LocalDate, Int) -> LocalDate = { currentDay, n ->
    var dayCounter = 1L
    var dayCounterShift = 1L
    var startDate = currentDay.with(adjustToFirstDayOfPeriod)
    if (n < 0) {
        dayCounter = -1
        dayCounterShift = -1
        startDate = currentDay.with(adjustToLastDay)
    }
    var iterated = findNextOrSameBusinessDay(startDate, dayCounterShift)
    while (!dayCounter.equals(n)) {
        iterated = findNextOrSameBusinessDay(iterated.plusDays(dayCounterShift), dayCounterShift)
        dayCounter = dayCounter.plus(dayCounterShift)
    }
    iterated
}

fun findNextOrSameBusinessDay(current: LocalDate, dayCounterShift: Long): LocalDate {
    var result = current
    while (isWeekend(result)) {
        result = result.plusDays(1)
    }
    return result
}

private fun findNDayInMonth(): (LocalDate, Int) -> LocalDate {
    return { currentDay: LocalDate, dayPosition: Int ->
        val yMonth = YearMonth.of(currentDay.year, currentDay.month)
        if (dayPosition > 0) {
            yMonth.atDay(dayPosition)
        } else {
            yMonth.atEndOfMonth().plusDays(dayPosition + 1L)
        }
    }
}

private fun jumpToNextMonth(): (LocalDate) -> LocalDate {
    return { currentDay: LocalDate ->
        currentDay.plusMonths(1)
    }
}




