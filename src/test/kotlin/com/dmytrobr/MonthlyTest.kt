package com.dmytrobr

import com.dmytrobr.data.Schedule
import com.dmytrobr.data.ScheduleType
import com.dmytrobr.data.toListOfDates
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.DateTimeException
import java.time.LocalDate.parse
import kotlin.test.expect


class MonthlyTest {
    @Test
    fun `4 days per month`() {
        assertThat(
                createSchedule("2017-07-19", "2017-08-20", false, -1, -2, 1, 2).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-07-30"),
                        parse("2017-07-31"),
                        parse("2017-08-01"),
                        parse("2017-08-02"))
    }

    @Test
    fun `second last day`() {
        assertThat(
                createSchedule("2017-07-19", "2017-08-20", false, nDay = *intArrayOf(-2)).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-07-30"))
    }

    @Test
    fun `2nd last day, 2 month period`() {
        assertThat(
                createSchedule("2017-07-19", "2017-09-20", false, nDay = *intArrayOf(-2)).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-07-30"),
                        parse("2017-08-30"))
    }

    @Test
    fun `fail to resolve 31st of September`() {
        assertThrows<DateTimeException>("September, 31st does not exist") {
            createSchedule("2017-09-01", "2017-10-20", false, 31).toListOfDates()

        }
    }


    @Test
    fun `verify end date is excluded`() {
        assertThat(
                createSchedule("2017-09-21", "2017-10-31", false, -1).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-09-30"))
    }


    @Test
    fun `verify dates before start date are excluded`() {
        assertThat(
                createSchedule("2017-09-21", "2017-10-31", false, 1).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-10-01"))
    }

    private fun createSchedule(startDate: String, endDate: String, isBusinessDay: Boolean = false, vararg nDay: Int) = Schedule(
            parse(startDate), parse(endDate), ScheduleType.MONTHLY, isBusinessDay, nDay.toList())
}