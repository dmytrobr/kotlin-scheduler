package com.dmytrobr.data

import com.dmytrobr.*
import java.time.LocalDate
import java.util.*

enum class ScheduleType {
    DAILY,
    WEEKLY,
    MONTHLY,
    QUARTERLY
}

data class Schedule(
        val startDate: LocalDate,
        val endDate: LocalDate,
        val scheduleType: ScheduleType,
        val businessDay: Boolean = false,
        val dayPositions: List<Int> = ArrayList())

fun Schedule.toListOfDates() =
        when (scheduleType) {
            ScheduleType.DAILY -> iterateWithCondition(this) {
                !this.businessDay ||
                        this.businessDay && !isWeekend(it)
            }
            ScheduleType.WEEKLY -> iterateWithCondition(this) {
                this.dayPositions.contains(it.dayOfWeek.value)
            }
            ScheduleType.MONTHLY -> {
                val result = LinkedList<LocalDate>()
                dayPositions.forEach {
                    for (date in startDate..endDate.minusDays(1) jumpStep findDayNextMonth(this, it)) {
                        result.add(date)
                    }
                }
                result
            }
            ScheduleType.QUARTERLY -> {
                val result = LinkedList<LocalDate>()
                dayPositions.forEach {
                    for (date in startDate..endDate.minusDays(1) jumpStep findNextDayInQuarter(this, it)) {
                        result.add(date)
                    }
                }
                result
            }
            else -> throw IllegalArgumentException("Can't schedule $scheduleType")
        }