package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.IsoFields
import java.time.temporal.Temporal
import java.time.temporal.TemporalAdjuster
import java.util.*


fun quarterlySchedule(schedule: Schedule): List<LocalDate> {

    var findNextDate = findNextNDayInQuarter()
    if (schedule.businessDay) {
        findNextDate = findNBusinessDayInQuarter()
    }
    val dates = ArrayList<LocalDate>()
    for (n in schedule.dayPositions) {
        DayPositionIterator(n, schedule.startDate, schedule.endDate, jumpToNextQuarter(), findNextDate).forEach { date ->
            dates.add(date)
        }
    }
    return dates

}

private fun findNBusinessDayInQuarter(): (LocalDate, Int) -> LocalDate = findNBusinessDayInPeriod(
        adjustToFirstDayOfPeriod = TemporalAdjuster { temporal: Temporal ->
            temporal.with(IsoFields.DAY_OF_QUARTER, temporal.range(IsoFields.DAY_OF_QUARTER).minimum)

        },
        adjustToLastDay = TemporalAdjuster { temporal ->
            temporal.with(IsoFields.DAY_OF_QUARTER, temporal.range(IsoFields.DAY_OF_QUARTER).maximum)
        })


fun findNextNDayInQuarter(): (LocalDate, Int) -> LocalDate =
        { date, n ->
            var shiftDay: Long = n.toLong()
            if (n < 0) {
                shiftDay = date.range(IsoFields.DAY_OF_QUARTER).maximum + n + 1
            }
            date.with(IsoFields.DAY_OF_QUARTER, shiftDay)

        }

//fun findNextNBusinessDayInQuarter(): (LocalDate, Int) -> LocalDate =
//        {
//            findNBusinessDayInPeriod(oneA,twoA)
//
//        }


private fun jumpToNextQuarter(): (LocalDate) -> LocalDate = { date -> date.plusMonths(3) }


