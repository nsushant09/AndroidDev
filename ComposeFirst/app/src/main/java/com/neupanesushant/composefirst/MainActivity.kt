package com.neupanesushant.composefirst

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neupanesushant.composefirst.ui.theme.ComposeFirstTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFirstTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Greeting(name = "Sushant")
                        Text("This is another text", fontSize = 14.sp)
                        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "This is a image", modifier = Modifier
                            .width(40.dp)
                            .height(80.dp))
                    }


                }
            }
        }


    }

    private fun intList(): List<Int> {
        return listOf(1, 2, 3, 4, 5, 6, 7 ,8)
    }

    fun addToList() : List<String>{
        return listOf("Sushant", "Neupane", "This", "Yogesh", "Bhatta", "Utsab", "Sapkota")
    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeFirstTheme {
        Greeting("Android")
    }
}