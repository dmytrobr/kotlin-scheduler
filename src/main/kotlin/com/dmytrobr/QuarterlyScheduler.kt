package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.IsoFields
import java.util.*


fun quarterlySchedule(schedule: Schedule): List<LocalDate> {

    var findNextDate = findNextNDayInQuarter()
    if (schedule.businessDay) {
        findNextDate = findNextNBusinessDayInQuarter()
    }
    val dates = ArrayList<LocalDate>()
    for (n in schedule.dayPositions) {

        DayPositionIterator(n, schedule.startDate, schedule.endDate, jumpToNextQuarter(), findNextDate).forEach { date ->
            dates.add(date)
        }

    }
    return dates

}

fun findNextNDayInQuarter(): (LocalDate, Int) -> LocalDate =
        { date, n ->
            var shiftDay: Long = n.toLong()
            if (n < 0) {
                shiftDay = date.range(IsoFields.DAY_OF_QUARTER).maximum + n + 1
            }
            date.with(IsoFields.DAY_OF_QUARTER, shiftDay)

        }

fun findNextNBusinessDayInQuarter(): (LocalDate, Int) -> LocalDate =
        {
            findNBusinessDayInPeriod(firstDayOfQuarter,lastDayOfQuarter)


        }


private fun jumpToNextQuarter(): (LocalDate) -> LocalDate = { date -> date.plusMonths(3) }


