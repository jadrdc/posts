package com.post.domain.models

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

data class Post(
    val id: Int,
    val description: String,
    val creationDate: String,
    val author: String,
    val url: String,
)

fun formatDate(creationDate: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val inputDateTime = LocalDateTime.parse(creationDate, formatter).atZone(ZoneOffset.UTC)

    val now = ZonedDateTime.now(ZoneOffset.UTC)
    return when {
        inputDateTime.toLocalDate().isEqual(now.toLocalDate()) -> {
            val hoursBetween = ChronoUnit.HOURS.between(inputDateTime, now)
            if (hoursBetween >= 1) {
                "${hoursBetween}h"

            } else {
                val minutesBetween = ChronoUnit.MINUTES.between(inputDateTime, now)
                "${minutesBetween}m"
            }
        }

        inputDateTime.toLocalDate().isEqual(now.toLocalDate().minusDays(1)) -> {
            "Yesterday."
        }

        else -> {
            val daysBetween =
                ChronoUnit.DAYS.between(inputDateTime.toLocalDate(), now.toLocalDate())
            "${daysBetween}d"
        }
    }
}