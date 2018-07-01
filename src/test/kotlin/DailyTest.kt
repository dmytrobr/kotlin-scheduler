package com.dmytrobr

import com.dmytrobr.data.Schedule
import com.dmytrobr.data.ScheduleType
import java.time.LocalDate.parse
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

@Test
fun testDailySchedule() {
    assertThat(dailySchedule(
            createSchedule("2017-07-29", "2017-07-20")))
            .containsExactlyInAnyOrder(
                    parse("2017-07-19"))
}


@Test
private fun testMultipleDaysAcrossMonth() {
    assertThat(dailySchedule(
            createSchedule("2017-07-29", "2017-08-02")))
            .containsExactlyInAnyOrder(
                    parse("2017-07-29"),
                    parse("2017-07-30"),
                    parse("2017-07-31"),
                    parse("2017-08-01"))
}

@Test
fun testBusinessDaysOnly() {
    assertThat(dailySchedule(
            createSchedule("2017-08-03", "2017-08-08")))
            .containsExactlyInAnyOrder(
                    parse("2017-08-03"),
                    parse("2017-08-04"),
                    parse("2017-08-07"))
}

private fun createSchedule(startDate: String, endDate: String) = Schedule(
        parse(startDate), parse(endDate), ScheduleType.DAILY)

