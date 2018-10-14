package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters
import java.util.*

fun findDayNextMonth(schedule: Schedule, nDay: Int): (LocalDate) -> LocalDate {
    return if (schedule.businessDay) {
        findNBusinessDayInPeriod(TemporalAdjusters.firstDayOfNextMonth(), TemporalAdjusters.lastDayOfMonth(), nDay)
    } else {
        findNDayInNextMonth(nDay)
    }
}

private fun findNDayInNextMonth(dayPosition: Int): (LocalDate) -> LocalDate = { currentDay: LocalDate ->
    val dateNextMonth = currentDay.plusMonths(1)
    val yMonth = YearMonth.of(dateNextMonth.year, dateNextMonth.month)
    if (dayPosition > 0) {
        yMonth.atDay(dayPosition)
    } else {
        yMonth.atEndOfMonth().plusDays(dayPosition + 1L)
    }
}



