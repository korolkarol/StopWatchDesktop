import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.formatTime
import java.time.LocalTime

class Clock {
    var formattedTime by mutableStateOf(formatTime())
    var coroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        coroutineScope.launch {
            while (true) {
                delay(250)
                formattedTime = LocalTime.now().toFormattedTime()
            }
        }
    }

}

fun LocalTime.toFormattedTime() = formatTime(
    hours = hour,
    minutes = minute,
    seconds = second,
)