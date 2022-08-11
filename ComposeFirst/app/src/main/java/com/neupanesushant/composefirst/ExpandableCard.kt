
package com.neupanesushant.composefirst

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.neupanesushant.composefirst.ui.theme.Shapes

@ExperimentalMaterialApi
@Composable
fun ExpandableCard(
    //make it customizeable by replacing title and descriptions when needed
){
    var expandedState by remember { mutableStateOf(false)}
    val rotationState by animateFloatAsState(targetValue = if(expandedState) 180f else 0f)

    Card(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 0,
                easing = LinearOutSlowInEasing
            )
        ),
        shape = Shapes.medium,
        onClick = {
            expandedState = !expandedState
        }
    ){

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)

        ){

            Row(verticalAlignment = Alignment.CenterVertically){
                Text(modifier = Modifier
                    .weight(6f),
                    text = "My title",
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                IconButton(modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .weight(1f)
                    .rotate(rotationState)
                    , onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "DropDown Icon")
                }
            }

            if(expandedState){
                Text(
                    text = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }

    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun ExpandableCardPreview(){
    ExpandableCard()
}