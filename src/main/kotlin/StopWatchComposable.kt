import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.StopWatchState
import model.TimeMeasurmeant

@Composable
fun stopWatchDisplay(
    timeMeasurement: TimeMeasurmeant,
    stopWatchState: StopWatchState,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    @Composable
    fun circleButton(onClick: () -> Unit, content: @Composable (RowScope.() -> Unit)) = Button(
        onClick = onClick,
        shape = CircleShape,
        content = content
    )

    @Composable
    fun playButton() = circleButton(
        onClick = onStartClick,
    ) {
        Icon(
            painter = painterResource("play.svg"),
            contentDescription = "Play"
        )
    }

    @Composable
    fun pauseButton() = circleButton(onPauseClick) {
        Icon(
            painter = painterResource("pause.svg"),
            contentDescription = "Pause"
        )
    }

    @Composable
    fun resetButton() = circleButton(onResetClick) {
        Icon(
            painter = painterResource("stop.svg"),
            contentDescription = "Reset"
        )
    }

    @Composable
    fun spacer() = Spacer(Modifier.height(16.dp))

    @Composable
    fun timeUnitRow() = Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = timeMeasurement.minutesString,
            fontWeight = FontWeight.Light,
            fontSize = 25.sp,
            color = Color.Black,
        )
        Text(
            text = if (timeMeasurement.seconds%2 == 0L) ":" else " ",
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            fontSize = 25.sp,
            color = Color.Black,
        )
        Text(
            text = timeMeasurement.secondsString,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.Black,
        )
        Text(
            text = ".",
            fontWeight = FontWeight.Normal,
            fontSize = 25.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Black,
        )
        Text(
            text = timeMeasurement.millisString,
            fontWeight = FontWeight.Thin,
            fontSize = 20.sp,
            color = Color.LightGray,
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        timeUnitRow()

        Spacer(Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier,
        ) {
            when (stopWatchState) {
                StopWatchState.ACTIVE -> {
                    pauseButton()
                    spacer()
                    resetButton()
                }
                StopWatchState.PAUSED -> {
                    playButton()
                    spacer()
                    resetButton()
                }
                StopWatchState.STOPPED -> {
                    playButton()
                }
            }

        }
    }
}