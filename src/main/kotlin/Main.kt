// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    MaterialTheme(
        colors = darkColors,
    ) {
        val modifier = Modifier.fillMaxWidth()
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
        ) {
            val stopWatch = remember { StopWatch() }
            val clock = remember { Clock() }

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
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
