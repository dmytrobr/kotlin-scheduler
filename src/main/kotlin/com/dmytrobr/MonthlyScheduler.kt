package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters
import java.util.*

fun monthlySchedule(schedule: Schedule): List<LocalDate> {
    val findNextNDayInMonth = if (schedule.businessDay) {
        findNBusinessDayInPeriod(TemporalAdjusters.firstDayOfMonth(), TemporalAdjusters.lastDayOfMonth())
    } else {
        findNDayInMonth()
    }
    val dates = ArrayList<LocalDate>()
    for (n in schedule.dayPositions) {
        findAllNDaysInPeriod(n, schedule.startDate, schedule.endDate, jumpToNextMonth(), findNextNDayInMonth).forEach { date ->
            dates.add(date)
        }

    }
    return dates
}


private fun findNDayInMonth(): (LocalDate, Int) -> LocalDate = { currentDay: LocalDate, dayPosition: Int ->
    val yMonth = YearMonth.of(currentDay.year, currentDay.month)
    if (dayPosition > 0) {
        yMonth.atDay(dayPosition)
    } else {
        yMonth.atEndOfMonth().plusDays(dayPosition + 1L)
    }
}


private fun jumpToNextMonth(): (LocalDate) -> LocalDate = { currentDay: LocalDate ->
    currentDay.plusMonths(1)
}





