package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.LocalDate
import java.time.temporal.IsoFields
import java.time.temporal.Temporal
import java.time.temporal.TemporalAdjuster
import java.util.*

fun findNextDayInQuarter(schedule: Schedule, nDay: Int): (LocalDate) -> LocalDate {
    return if (schedule.businessDay) {
        findNBusinessDayInQuarter(nDay)
    } else {
        findNextNDayInQuarter(nDay)
    }
}

private fun findNBusinessDayInQuarter(nDay: Int) = findNBusinessDayInPeriod(
        TemporalAdjuster { temporal: Temporal ->
            temporal.with(IsoFields.DAY_OF_QUARTER, temporal.range(IsoFields.DAY_OF_QUARTER).minimum)

        },
        TemporalAdjuster { temporal ->
            temporal.with(IsoFields.DAY_OF_QUARTER, temporal.range(IsoFields.DAY_OF_QUARTER).maximum)
        }, nDay)


fun findNextNDayInQuarter(nDay: Int): (LocalDate) -> LocalDate =
        { date ->
            var shiftDay: Long = nDay.toLong()
            if (nDay < 0) {
                shiftDay = date.range(IsoFields.DAY_OF_QUARTER).maximum + nDay + 1
            }
            date.with(IsoFields.DAY_OF_QUARTER, shiftDay)

        }


private fun jumpToNextQuarter(): (LocalDate) -> LocalDate = { date -> date.plusMonths(3) }


