package com.dmytrobr

import com.dmytrobr.data.Schedule
import com.dmytrobr.data.ScheduleType
import com.dmytrobr.data.toListOfDates
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate.parse


class QuarterlyBusinessDayTest {
    @Test
    fun `4 business days per quarter`() {
        assertThat(
                createSchedule("2018-01-19", "2019-01-01", true, 6, 9, -14, -5).toListOfDates())
                .containsExactlyInAnyOrder(
                        //1st
                        parse("2018-03-30"), parse("2018-03-29"),
                        //2nd Quarter
                        parse("2018-04-03"), parse("2018-04-02"),
                        parse("2018-06-28"), parse("2018-06-29"),
                        //3rd Quarter
                        parse("2018-07-03"), parse("2018-07-02"),
                        parse("2018-09-27"), parse("2018-09-28"),
                        //4th Quarter
                        parse("2018-10-01"), parse("2018-10-02"),
                        parse("2018-12-28"), parse("2018-12-31"))
    }

    @Test
    fun `1 business day per quarter`() {
        assertThat(
                createSchedule("2018-03-19", "2018-10-31", true, 2).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2018-04-03"),
                        parse("2018-07-03"),
                        parse("2018-10-02"))
    }


    @Test
    fun `verify end date is excluded`() {
        assertThat(
                createSchedule("2018-04-21", "2018-06-29", true, -1).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-04-30"))
    }


    @Test
    fun `verify dates before start date are excluded`() {
        assertThat(
                createSchedule("2018-01-21", "2017-03-31", true, 10).toListOfDates())
                .isEmpty()
    }

    private fun createSchedule(startDate: String, endDate: String, isBusinessDay: Boolean, vararg nDay: Int) = Schedule(
            parse(startDate), parse(endDate), ScheduleType.QUARTERLY, isBusinessDay, nDay.toList())
}