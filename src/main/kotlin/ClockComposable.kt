import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import model.FormattedTime

@Composable
fun clockDisplay(
    formattedTime: FormattedTime,
    modifier: Modifier,
) = Row(
    horizontalArrangement = Arrangement.Center,
    modifier = modifier
) {
    Text(
        text = formattedTime.hours,
        fontWeight = FontWeight.Light,
        fontSize = 30.sp,
        color = Color.Black,
    )
    Text(
        text = ":",
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Monospace,
        fontSize = 30.sp,
        color = Color.Black,
    )
    Text(
        text = formattedTime.minutes,
        fontWeight = FontWeight.Light,
        fontSize = 30.sp,
        color = Color.Black,
    )
    Text(
        text = ":",
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Monospace,
        fontSize = 30.sp,
        color = Color.Black,
    )
    Text(
        text = formattedTime.seconds,
        fontWeight = FontWeight.Thin,
        fontSize = 30.sp,
        color = Color.DarkGray,
    )
}