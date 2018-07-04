package com.dmytrobr

import com.dmytrobr.data.Schedule
import com.dmytrobr.data.ScheduleType
import com.dmytrobr.data.toListOfDates
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate.parse


class MonthlyTest {
    @Test
    fun `4 business days per month`() {
        assertThat(
                createSchedule("2017-07-12", "2017-08-20", true, 6, 9, -14, -5).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-07-12"),
                        parse("2017-07-13"),
                        parse("2017-07-25"),
                        parse("2017-08-08"),
                        parse("2017-08-11"),
                        parse("2017-08-14"))
    }


    @Test
    fun `last day of month`() {
        assertThat(
                createSchedule("2017-07-12", "2017-08-20", true, -1).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-07-31"))
    }

    @Test
    fun `verify end date is excluded`() {
        assertThat(
                createSchedule("2017-09-21", "2017-10-31", true, -1).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-09-29"))
    }


    @Test
    fun `verify dates before start date are excluded`() {
        assertThat(
                createSchedule("2017-09-21", "2017-10-31", true, 1).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-10-02"))
    }

    private fun createSchedule(startDate: String, endDate: String, isBusinessDay: Boolean, vararg nDay: Int) = Schedule(
            parse(startDate), parse(endDate), ScheduleType.MONTHLY, isBusinessDay, nDay.toList())
}