package com.neupanesushant.composefirst

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Column(
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.5f)
                    .padding(top = 50.dp)
            ) {
                Text("Hello", modifier = Modifier
                    .offset()
                    .clickable {
                        Toast
                            .makeText(applicationContext, "Clicked Hello", Toast.LENGTH_SHORT)
                            .show()
                    })
                Spacer(modifier = Modifier.height(30.dp))
                Text("World")
                ImageCard(painterResource(id = R.drawable.ic_launcher_background), "Icon Image", "This is the title")
            }
        }

    }


}

@Composable
fun ImageCard(
    painter : Painter,
    contentDescription : String,
    title : String,
    modifier : Modifier = Modifier
){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 8.dp
    ){
        Box(modifier = Modifier.height(200.dp), contentAlignment = Alignment.Center){
            Image(painter = painter, contentDescription = contentDescription, contentScale = ContentScale.Crop, alignment = Alignment.Center)
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp), contentAlignment = Alignment.BottomCenter){
                Text(title, style = TextStyle(color = Color.Black, fontSize = 16.sp))
            }

        }
    }
}



