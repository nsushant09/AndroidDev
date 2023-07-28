package com.neupanesushant.composefrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.neupanesushant.composefrontend.ui.theme.ComposeFrontEndTheme
import java.util.Locale


class MainActivity : ComponentActivity() {


    private val imageLink =
        "https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%3D&w=1000&q=80"
    private val users = listOf(
        User("Sushant Neupane", imageLink),
        User("Yogesh Bhatta", imageLink),
        User("Suprit Gautam", imageLink),
        User("Utsab Sapkota", imageLink),
        User("Pratyush Adhikari", imageLink),
        User("Sushant Neupane", imageLink),
        User("Sushant Neupane", imageLink),
        User("Sushant Neupane", imageLink),
        User("Sushant Neupane", imageLink),
        User("Sushant Neupane", imageLink),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFrontEndTheme {

                val textValue = remember {
                    mutableStateOf(TextFieldValue(""))
                }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        SearchBar(textValue = textValue)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("All Users".toUpperCase(Locale.ROOT))
                        AlignBodyRow(users = users)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("All Grid".toUpperCase(Locale.ROOT))
                        FavoriteCollectionGrid()
                        Text(text = "Hello")

                    }
                }
            }
        }
    }


    @Composable
    fun SearchBar(
        textValue: MutableState<TextFieldValue>,
        modifier: Modifier = Modifier
    ) {
        TextField(
            value = textValue.value,
            onValueChange = {
                textValue.value = it
            },
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            },
            placeholder = {
                Text(text = "Search...")
            },
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp)
        )
    }

    @Composable
    fun AlignBodyRow(
        users: List<User>,
        modifier: Modifier = Modifier
    ) {
        LazyRow(modifier = modifier) {
            items(users) { item ->
                AlignBodySection(user = item)
            }
        }
    }

    @Composable
    fun AlignBodySection(
        user: User,
        modifier: Modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(80.dp)
        ) {
            CustomImage(
                imageUrl = user.image,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Text(
                text = user.name,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun CustomImage(imageUrl: String, modifier: Modifier = Modifier) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }

    @Preview
    @Composable
    fun FavoriteCollectionCard(
        modifier: Modifier = Modifier
    ) {
        Surface(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomImage(
                    imageUrl = imageLink,
                    modifier = Modifier
                        .size(64.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Text Content",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    fun FavoriteCollectionGrid(
        modifier: Modifier = Modifier
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2), modifier = Modifier.height(150.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                FavoriteCollectionCard(modifier = Modifier.wrapContentHeight())
            }
        }
    }
}