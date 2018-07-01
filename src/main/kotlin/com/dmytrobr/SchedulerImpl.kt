package com.dmytrobr

import com.dmytrobr.data.Schedule
import com.dmytrobr.data.ScheduleType
import java.time.LocalDate

class SchedulerImpl : Scheduler {
    override fun schedule(schedule: Schedule): List<LocalDate> {
        return when (schedule.scheduleType) {
            ScheduleType.DAILY -> dailySchedule(schedule)
            ScheduleType.WEEKLY -> weeklySchedule(schedule)
            ScheduleType.MONTHLY -> monthlySchedule(schedule)
            ScheduleType.QUARTERLY -> quarterlySchedule(schedule)
            else -> throw IllegalArgumentException("Can't schedule " + schedule.scheduleType)
        }
    }
}