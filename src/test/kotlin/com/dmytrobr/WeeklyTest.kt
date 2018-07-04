package com.dmytrobr

import com.dmytrobr.data.Schedule
import com.dmytrobr.data.ScheduleType
import com.dmytrobr.data.toListOfDates
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate.parse


class WeeklyTest {
    @Test
    fun `test one day`() {
        assertThat(
                createSchedule("2017-07-19", "2017-07-26", 2).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-07-25"))
    }


    @Test
    private fun `test multiple days across months`() {
        assertThat(
                createSchedule("2017-07-28", "2017-08-03", 3, 5).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-07-28"),
                        parse("2017-08-02"))
    }


    private fun createSchedule(startDate: String, endDate: String, vararg nDay: Int) = Schedule(
            parse(startDate), parse(endDate), ScheduleType.WEEKLY, dayPositions = nDay.toList())

}