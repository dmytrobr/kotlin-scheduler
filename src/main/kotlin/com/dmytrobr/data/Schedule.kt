package com.dmytrobr.data

import java.time.LocalDate

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
        val dayPositions: List<Int> = ArrayList(),
        val businessDay: Boolean=false)