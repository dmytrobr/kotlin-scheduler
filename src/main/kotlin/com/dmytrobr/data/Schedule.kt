package com.dmytrobr.data

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
