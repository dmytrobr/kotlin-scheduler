package com.dmytrobr

import java.time.LocalDate
import java.util.*

/**
 *
 */
class DayPositionIterator(private val dayPosition: Int,
                          startDate: LocalDate,
                          private val endDate: LocalDate,
                          private val shiftToNextPeriod: (LocalDate) -> LocalDate,
                          private val findNextDate: (LocalDate, Int) -> LocalDate) : Iterator<LocalDate> {
    var nextDate = findNextDate(startDate, dayPosition)

    init {
        if (nextDate.isBefore(startDate))
            nextDate = findNextDate(shiftToNextPeriod(nextDate), dayPosition)
    }

    override fun hasNext(): Boolean {
        return nextDate.isBefore(endDate)
    }

    override fun next(): LocalDate {
        if (!hasNext()) throw NoSuchElementException()

        val result = nextDate
        nextDate = findNextDate(shiftToNextPeriod(nextDate), dayPosition)
        return result
    }

}