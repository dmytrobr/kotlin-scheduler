package com.dmytrobr.data

import com.dmytrobr.dailySchedule
import com.dmytrobr.monthlySchedule
import com.dmytrobr.quarterlySchedule
import com.dmytrobr.weeklySchedule
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
            ScheduleType.DAILY -> dailySchedule(this)
            ScheduleType.WEEKLY -> weeklySchedule(this)
            ScheduleType.MONTHLY -> monthlySchedule(this)
            ScheduleType.QUARTERLY -> quarterlySchedule(this)
            else -> throw IllegalArgumentException("Can't schedule $scheduleType")
        }