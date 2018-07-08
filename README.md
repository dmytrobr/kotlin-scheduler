# Kotlin Scheduler

Need to schedule something each 2nd last day of the month? Or maybe last
Thursday of the month? What about each quarter or weekly schedules?
kotlin-scheduler library answers such questions


## Using Scheduler
Build a jar file with Gradle and add it to the classpath of your Java or Kotlin app

### Build locally from source code

You need JDK 8+ and Gradle installed prior to build (see version below, later versions should work as well, but did not verified with any earlier versions)
```
kotlin-scheduler>gradle -version

------------------------------------------------------------
Gradle 4.8.1
------------------------------------------------------------

Build time:   2018-06-21 07:53:06 UTC
Revision:     0abdea078047b12df42e7750ccba34d69b516a22

Groovy:       2.4.12
Ant:          Apache Ant(TM) version 1.9.11 compiled on March 23 2018
JVM:          1.8.0_141 (Oracle Corporation 25.141-b15)
OS:           Windows 10 10.0 amd64

gradle clean build
```


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
