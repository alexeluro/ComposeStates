package com.example.composestates

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composestates.ui.theme.ComposeStatesTheme
import com.example.composestates.ui.theme.Shapes
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStatesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ColorBox(modifier = Modifier)
                }
            }
        }
    }
}

private fun slideColorShow(updatedColor: (Color) -> Unit) {
    updatedColor(
        Color(
            Random.nextFloat(),
            Random.nextFloat(),
            Random.nextFloat(),
            1f
        )
    )
}

@Composable
fun ColorBox(modifier: Modifier) {
    val colorState = remember {
        mutableStateOf(Color.Yellow)
    }

    val animateColorState by animateColorAsState(targetValue = colorState.value)
    val animatedTextColor by animateColorAsState(
        targetValue = Color(
            colorState.value.green,
            colorState.value.blue,
            colorState.value.red,
            1f
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(animateColorState),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier
                .fillMaxSize()
                .background(animateColorState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Compose State",
                style = TextStyle(
                    fontSize = 30.sp,
                    color = animatedTextColor,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(
                modifier = modifier
                    .height(16.dp)
            )
            Button(
                onClick = { slideColorShow { colorState.value = it } },
            ) {
                Text(text = "Next Color")
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStatesTheme {
        ColorBox(modifier = Modifier)
    }
}