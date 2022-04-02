package model

data class TimeMeasurmeant(
    val hours: Long = 0,
    val minutes: Long = 0,
    val seconds: Long = 0,
    val millis: Long = 0,
) {
    val hoursString = String.format("%02d", hours)
    val minutesString = String.format("%02d", minutes)
    val secondsString = String.format("%02d", seconds)
    val millisString = String.format("%03d", millis)
}
