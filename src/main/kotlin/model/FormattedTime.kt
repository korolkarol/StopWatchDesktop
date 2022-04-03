package model

data class FormattedTime(
    val hours: String,
    val minutes: String,
    val seconds: String,
    val millis: String,
)

fun formatTime(
    hours: Int = 0,
    minutes: Int = 0,
    seconds: Int = 0,
    millis: Int = 0
) = FormattedTime(
    hours = String.format("%02d", hours),
    minutes = String.format("%02d", minutes),
    seconds = String.format("%02d", seconds),
    millis = String.format("%03d", millis),
)