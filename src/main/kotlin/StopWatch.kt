import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import model.StopWatchState
import model.TimeMeasurmeant

private fun Long.asTimeMeasurement() = TimeMeasurmeant(
    hours = this / 1000 / 60 / 60,
    minutes = this / 1000 / 60 % 60,
    seconds = this / 1000 % 60,
    millis = this % 1000,
)


class StopWatch {
    var timeMeasurement by mutableStateOf(TimeMeasurmeant())
    var stopWatchState by mutableStateOf(StopWatchState.STOPPED)

    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    private var lastTimeMillis: Long = 0
    private var passedTimeMillis: Long = 0

    fun start() {
        if (stopWatchState == StopWatchState.ACTIVE) return

        coroutineScope.launch {
            lastTimeMillis = System.currentTimeMillis()
            stopWatchState = StopWatchState.ACTIVE
            while (stopWatchState == StopWatchState.ACTIVE) {
                delay(10)
                System.currentTimeMillis().let { currentTimeMillis ->
                    passedTimeMillis += currentTimeMillis - lastTimeMillis
                    lastTimeMillis = currentTimeMillis
                }
                timeMeasurement = passedTimeMillis.asTimeMeasurement()
            }
        }
    }

    fun pause() {
        stopWatchState = StopWatchState.PAUSED
    }

    fun restart() {
        pause()
        coroutineScope.cancel()
        coroutineScope = CoroutineScope(Dispatchers.Main)
        stopWatchState = StopWatchState.STOPPED
        passedTimeMillis = 0
        lastTimeMillis = 0
    }

}