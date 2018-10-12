package com.dmytrobr

import com.dmytrobr.data.Schedule
import com.dmytrobr.data.ScheduleType
import com.dmytrobr.data.toListOfDates
import java.time.LocalDate.parse
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class DailyTest {
    @Test
    fun testDailySchedule() {
        assertThat(
                createSchedule("2017-07-29", "2017-07-30", false).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-07-29"))
    }


    @Test
    fun testMultipleDaysAcrossMonth() {
        assertThat(
                createSchedule("2017-07-29", "2017-08-02", false).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-07-29"),
                        parse("2017-07-30"),
                        parse("2017-07-31"),
                        parse("2017-08-01"))
    }

    @Test
    fun testBusinessDaysOnly() {
        assertThat(
                createSchedule("2017-08-03", "2017-08-08", true).toListOfDates())
                .containsExactlyInAnyOrder(
                        parse("2017-08-03"),
                        parse("2017-08-04"),
                        parse("2017-08-07"))
    }

    private fun createSchedule(startDate: String, endDate: String, businessDays: Boolean) = Schedule(
            parse(startDate), parse(endDate), ScheduleType.DAILY, businessDays)

}