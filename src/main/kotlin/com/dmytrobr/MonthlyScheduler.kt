package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.util.*

fun monthlySchedule(schedule: Schedule): List<LocalDate> {
    val findNextNDayInMonth: (LocalDate, Int) -> LocalDate = if (schedule.businessDay) {
        findNDayInMonth()
    } else {
        findNBusinessDayInMonth()
    }

    val dates = ArrayList<LocalDate>()
    for (n in schedule.dayPositions) {
        DayPositionIterator(n, schedule.startDate, schedule.endDate, jumpToNextMonth(),findNextNDayInMonth)
    }
    return dates
}

fun findNBusinessDayInMonth(): (LocalDate, Int) -> LocalDate {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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



fun findNextNDayInMonth(currentDay: LocalDate, n: Int) {

}


