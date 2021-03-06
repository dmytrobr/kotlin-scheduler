package com.dmytrobr

import com.dmytrobr.data.Schedule
import com.dmytrobr.data.ScheduleType
import com.dmytrobr.data.toListOfDates
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate.parse


class QuarterlyTest {
    @Test
    fun `4 days per quarter`() {
        assertThat(
                createSchedule("2018-01-19", "2019-01-01", false, -1, -2, 1, 2).toListOfDates())
                .containsExactlyInAnyOrder(
                        //1st
                        parse("2018-03-30"), parse("2018-03-31"),
                        //2nd Quarter
                        parse("2018-04-01"), parse("2018-04-02"),
                        parse("2018-06-30"), parse("2018-06-29"),
                        //3rd Quarter
                        parse("2018-07-01"), parse("2018-07-02"),
                        parse("2018-09-29"), parse("2018-09-30"),
                        //4th Quarter
                        parse("2018-10-01"), parse("2018-10-02"),
                        parse("2018-12-30"), parse("2018-12-31"))
    }

    @Test
    fun `1 business day per quarter`() {
        assertThat(
                createSchedule("2018-03-19", "2018-06-30", false, 2).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2018-04-02"))
    }


    @Test
    fun `verify end date is excluded`() {
        assertThat(
                createSchedule("2018-04-21", "2018-06-29", false, -1).toListOfDates())
                .isEmpty()
    }


    @Test
    fun `verify dates before start date are excluded`() {
        assertThat(
                createSchedule("2018-01-21", "2017-03-31", false, 20).toListOfDates())
                .isEmpty()
    }

    private fun createSchedule(startDate: String, endDate: String, isBusinessDay: Boolean, vararg nDay: Int) = Schedule(
            parse(startDate), parse(endDate), ScheduleType.QUARTERLY, isBusinessDay, nDay.toList())
}