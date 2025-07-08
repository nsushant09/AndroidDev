package com.kotlinproject.blog

import AddBlogScreen
import FeedItem
import HomeScreen
import ProfileScreen
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.kotlinproject.blog.ui.theme.BlogTheme
import com.kotlinproject.blog.viewmodel.MainViewModel
import data.FirebaseInstance
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    companion object {
        val feedItems = listOf(
            FeedItem(
                id = "1",
                title = "Please Start Writing Better Git Commits",
                excerpt = "I recently read a helpful article on Hashnode by Simon Egersand titled “Write Git Commit Messages Your Colleagues Will Love...",
                author = "New blogger",
                date = "Jul 29, 2022",
                isBookmarked = true
            ),
            FeedItem(
                id = "2",
                title = "About criticism",
                excerpt = "Everybody is a critic. Every developer has both been on the receiving and the giving end of criticism. It is a vital part of our job...",
                author = "Aman blogger",
                date = "Jul 20, 2022",
                isBookmarked = false
            ),
            FeedItem(
                id = "3",
                title = "About criticism",
                excerpt = "Everybody is a critic. Every developer has both been on the receiving and the giving end of criticism...",
                author = "Old blogger",
                date = "Jul 20, 2022",
                isBookmarked = false
            )
        )
    }

    val mainViewModel: MainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            BlogTheme {
                AppNavigator(this, mainViewModel)
            }
        }
    }
}

@Composable
fun AppNavigator(context: Context, mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("welcome") {
            WelcomeScreen(
                navController,
                { navController.navigate("login") },
                { navController.navigate("register") })
        }

        composable("login") {
            LoginScreen(
                context = context,
                mainViewModel,
                onLoginSuccess = {
                    navController.navigate("home")
                    {
                        popUpTo(0) { inclusive = true } // clears everything
                        launchSingleTop = true          // prevents duplicate destination on top
                    }
                },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen(
                context = context,
                viewModel = mainViewModel,
                onRegisterSuccess = { user ->
                    navController.navigate("home") {
                        popUpTo(0) { inclusive = true } // clears everything
                        launchSingleTop = true          // prevents duplicate destination on top
                    }
                },
            )
        }

        composable("home") {
            HomeScreen(navController, mainViewModel)
        }

        composable("readmore") {
            ReadMoreScreen(navController, mainViewModel)
        }

        composable("add_blog") {
            AddBlogScreen(navController, mainViewModel, context)
        }

        composable("bookmark") {
            BookmarkedScreen(navController, mainViewModel, context)
        }

        composable("profile") {
            ProfileScreen(navController, mainViewModel, context)
        }

    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000) // 2 second delay
        if (FirebaseInstance.firebaseAuth.currentUser == null) {
            navController.navigate("welcome")
        } else {
            navController.navigate("home")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("Blog.", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}