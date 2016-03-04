package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other);
}

operator fun MyDate.plus(timeInterval: TimeInterval) : MyDate {
    return this.addTimeIntervals(timeInterval, 1)
}

operator fun MyDate.plus(timeInterval: RepeatedTimeInterval) : MyDate {
    return this.addTimeIntervals(timeInterval.timeInterval , timeInterval.number)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)
operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {

    override fun iterator(): Iterator<MyDate> {
        return DateIterator(this)
    }

    operator fun contains(d: MyDate): Boolean {
        return d > start && d < endInclusive
    }
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {

    var current = dateRange.start

    override fun hasNext(): Boolean = current <= dateRange.endInclusive

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }
}
