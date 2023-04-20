package com.neupanesushant.jetpackcompose

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neupanesushant.jetpackcompose.ui.theme.JetpackComposeTheme
import com.neupanesushant.jetpackcompose.ui.theme.LightYellow
import com.neupanesushant.jetpackcompose.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val names: List<String> = arrayListOf("Barsha", "Sushant", "Yogesh", "Utsab")
        setContent {
            JetpackComposeTheme() {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier.defaultMinSize(),
                    ) {
                        for (name in names) {
                            GreetingCard(name = name);
                        }
                    }
                }
            }
        }//setContent End
    }
}

@Composable
fun GreetingCard(name: String) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 16.dp

    Surface(
        color = LightYellow,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                bottom = extraPadding,
                end = 16.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Hello,\n$name",
                modifier = Modifier.padding(24.dp),
                style = Typography.body1
            );
            Button(
                onClick = { expanded.value = !expanded.value },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(Color.White, Color.Black)
            ) {
                Text(text = if (expanded.value) "Show Less" else "Show More");
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
    }
}