import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
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
    modifier: Modifier = Modifier.fillMaxSize()
) {
    @Composable
    fun circleButton(
        onClick: () -> Unit = {},
        active: Boolean = true,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colors.primary,
        disabledColor: Color = Color.LightGray,
        content: @Composable (() -> Unit) = {},
    ) = Surface(
        shape = CircleShape,
        modifier = modifier.clickable { if (active) onClick() }.width(35.dp).height(35.dp),
        content = content,
        color = if (active) color else disabledColor
    )

    @Composable
    fun buttonSpace() = circleButton(
        color = Color.White
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
    fun resumeButton() = circleButton(
        onClick = onStartClick,
    ) {
        Icon(
            imageVector = Icons.Rounded.Refresh,
            contentDescription = "Resume"
        )
    }

    @Composable
    fun pauseButton(active: Boolean = true) = circleButton(
        onClick = onPauseClick,
        active = active,
    ) {
        Icon(
            painter = painterResource("pause.svg"),
            contentDescription = "Pause"
        )
    }

    @Composable
    fun resetButton(active: Boolean = true) = circleButton(
        onClick = onResetClick,
        active = active
    ) {
        Icon(
            painter = painterResource("stop.svg"),
            contentDescription = "Reset"
        )
    }

    @Composable
    fun spacer() = Spacer(Modifier.height(16.dp))

    @Composable
    fun timeUnitRow() = Row {
        Text(
            text = timeMeasurement.minutesString,
            fontWeight = FontWeight.Light,
            fontSize = 25.sp,
            color = Color.Black,
        )
        Text(
            text = if (timeMeasurement.seconds % 2 == 0L) ":" else " ",
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

    @Composable
    fun buttons() = Row {
        when (stopWatchState) {
            StopWatchState.ACTIVE -> {
                buttonSpace()
                spacer()
                pauseButton()
                spacer()
                resetButton()
            }
            StopWatchState.PAUSED -> {
                resumeButton()
                spacer()
                pauseButton(active = false)
                spacer()
                resetButton()
            }
            StopWatchState.STOPPED -> {
                playButton()
                spacer()
                buttonSpace()
                spacer()
                buttonSpace()
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Column {
            timeUnitRow()
            spacer()
            buttons()
        }
    }
}