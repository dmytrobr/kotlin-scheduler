package com.dmytrobr

import com.dmytrobr.data.Schedule
import java.time.LocalDate

interface Scheduler {
    fun schedule(schedule: Schedule): List<LocalDate>
}