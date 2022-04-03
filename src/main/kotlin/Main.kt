// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    val darkColors = darkColors(
        primary = Color(0xFFFFEB3B),
        secondary = Color(0xFFF0F4C3),
        background = Color.DarkGray
    )

    val modifier = Modifier.fillMaxWidth()
    MaterialTheme(
        colors = darkColors,
    ) {
        val stopWatch = remember { StopWatch() }
        val clock = remember { Clock() }

        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    navigationIcon = {
                        Icon(
                            painter = painterResource("timer.svg"),
                            contentDescription = "StopWatch"
                        )
                    },
                    title = { Text("StopWatch") }
                )
            },
            content = {
                Column {
                    clockDisplay(
                        formattedTime = clock.formattedTime,
                        modifier = modifier
                    )

                    stopWatchDisplay(
                        timeMeasurement = stopWatch.timeMeasurement,
                        stopWatchState = stopWatch.stopWatchState,
                        onStartClick = stopWatch::start,
                        onPauseClick = stopWatch::pause,
                        onResetClick = stopWatch::restart,
                        modifier = modifier
                    )
                }
            }
        )
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
