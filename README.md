# Kotlin Scheduler

Need to schedule something each 2nd last day of the month? Or maybe last
Thursday of the month? What about each quarter or weekly schedules?
kotlin-scheduler library answers such questions


## Using Scheduler
Build a jar file with Gradle and add it to the classpath of your Java or Kotlin app

### Example in Kotlin
```kotlin
  val runDates=Schedule(startDate= LocalDate.parse("2018-07-05"),
           endDate =  LocalDate.parse("2018-10-25"),
           scheduleType = ScheduleType.MONTHLY,
           businessDay = true,
           dayPositions = asList(1,2,-3))
           .toListOfDates()
```
See more usage examples in src/test/kotlin

## Feature List
* Daily, Weekly, Monthly, Quarterly schedules
* Business Days for each schedule type
* Day of the week for mothly schedules (e.g. each 3rd Tuesday)
