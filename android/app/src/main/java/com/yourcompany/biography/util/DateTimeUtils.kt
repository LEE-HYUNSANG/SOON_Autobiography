package com.yourcompany.biography.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateTimeUtils @Inject constructor() {
    private val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault())

    fun nowIso(): String = Instant.now().toString()

    fun format(timestamp: Long): String =
        formatter.format(Instant.ofEpochMilli(timestamp))
}
