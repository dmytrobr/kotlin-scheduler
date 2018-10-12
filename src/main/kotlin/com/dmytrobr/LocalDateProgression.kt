package com.dmytrobr

import java.time.LocalDate

class LocalDateProgression(override val start: LocalDate,
                           override val endInclusive: LocalDate,
                           val condition: (LocalDate) -> Boolean = { true }) : Iterable<LocalDate>, ClosedRange<LocalDate> {
    override fun iterator(): Iterator<LocalDate> = LocalDateProgressionIterator(start, endInclusive, condition)
    infix fun condition(condition: (LocalDate) -> Boolean) = LocalDateProgression(start, endInclusive, condition)
}

internal class LocalDateProgressionIterator(val start: LocalDate, val endInclusive: LocalDate, val condition: (LocalDate) -> Boolean) : Iterator<LocalDate> {
    var current = findNext(start)

    override fun hasNext() = current <= endInclusive && condition(current)
    override fun next(): LocalDate {
        val result = current
        current = findNext(current.plusDays(1))
        return result;
    }

    fun findNext(startDate: LocalDate): LocalDate {
        var result = startDate
        while (result.isBefore(endInclusive) && !condition(result)) {
            result = result.plusDays(1)
        }
        return result
    }
}

operator fun LocalDate.rangeTo(other: LocalDate) = LocalDateProgression(this, other)