package com.dmytrobr

import java.time.LocalDate

internal class LocalDateProgressionIterator(val start: LocalDate, val endInclusive: LocalDate, val condition: (LocalDate) -> Boolean, val findNext: (LocalDate) -> LocalDate) : Iterator<LocalDate> {
    var current = findNext(start)

    override fun hasNext() = current <= endInclusive && condition(current)
    override fun next(): LocalDate {
        val result = current
        current = findNext(current.plusDays(1))
        return result;
    }
}

operator fun LocalDate.rangeTo(other: LocalDate) = LocalDateProgression(this, other)
class LocalDateProgression(override val start: LocalDate,
                           override val endInclusive: LocalDate,
                           val condition: (LocalDate) -> Boolean = { true },
                           val findNextFunction: (LocalDate) -> LocalDate = defaultFindNext(start, endInclusive, condition)) : Iterable<LocalDate>, ClosedRange<LocalDate> {
    override fun iterator(): Iterator<LocalDate> = LocalDateProgressionIterator(start, endInclusive, condition, findNextFunction)
    infix fun condition(condition: (LocalDate) -> Boolean) = LocalDateProgression(start, endInclusive, condition)
    infix fun jumpStep(findNext: (LocalDate) -> LocalDate) = LocalDateProgression(start, endInclusive, findNextFunction = findNext)
}


private fun defaultFindNext(start: LocalDate, endInclusive: LocalDate, condition: (LocalDate) -> Boolean): (LocalDate) -> LocalDate {
    return {
        var result = start
        while (result.isBefore(endInclusive) && !condition(result)) {
            result = result.plusDays(1)
        }
        result
    }
}