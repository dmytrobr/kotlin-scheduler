package com.dmytrobr

import com.dmytrobr.data.Schedule
import com.dmytrobr.data.ScheduleType
import java.time.LocalDate.parse
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

@Test
fun testDailySchedule() {
    assertThat(dailySchedule(
            Schedule(parse("2017-07-29"), parse("2017-07-20"), ScheduleType.DAILY)))
            .containsExactlyInAnyOrder(parse("2017-07-19"))
}