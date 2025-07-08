// ──────────────────────────────────────────────────────────────────────────
// imports
// ──────────────────────────────────────────────────────────────────────────
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kotlinproject.blog.viewmodel.MainViewModel
import data.FirebaseInstance
import domain.Utils

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: MainViewModel,
    context: Context
) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<FeedItem?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            /* ── Top bar ─────────────────────────────────────────────── */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }

                Text(
                    text = "Profile",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Button(
                    onClick = { showLogoutDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text("Logout", color = Color.White)
                }
            }

            /* ── User name ──────────────────────────────────────────── */
            Text(
                text = viewModel.currentUser?.name ?: "Unknown User",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )

            Spacer(Modifier.height(8.dp))

            /* ── Section header ─────────────────────────────────────── */
            Text(
                text = "Your articles",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            /* ── Article list ───────────────────────────────────────── */
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewModel.myFeedItems) { item ->
                    ArticleCard(
                        item = item,
                        onReadMore = {
                            viewModel.selectFeedItem(item)
                            navController.navigate("readmore")
                        },
                        onEdit = {
                            viewModel.selectFeedItem(item)
                            navController.navigate("add_blog")
                        },
                        onDelete = {
                            itemToDelete = item
                            showDeleteDialog = true
                        }
                    )
                }
            }
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDeleteDialog = false
                    itemToDelete = null
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDeleteDialog = false
                        itemToDelete?.let {
                            viewModel.deleteFeedItem(it.id, {
                                Utils.showToast(context, "Blog deleted successfully")
                            }, {
                                Utils.showToast(context, "Blog could not be deleted")
                            })
                        }
                    }) {
                        Text("Delete", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Confirm Deletion") },
                text = { Text("Are you sure you want to delete this article? This action cannot be undone.") }
            )
        }

        /* ── Logout dialog ─────────────────────────────────────────── */
        if (showLogoutDialog) {
            AlertDialog(
                onDismissRequest = { showLogoutDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        showLogoutDialog = false
                        navController.navigate("welcome") {
                            popUpTo(0) { inclusive = true } // clears everything
                            launchSingleTop = true          // prevents duplicate destination on top
                        }
                        FirebaseInstance.firebaseAuth.signOut()
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showLogoutDialog = false }) { Text("No") }
                },
                title = { Text("Log out") },
                text = { Text("Are you sure you want to log out?") }
            )
        }
    }
}

// ──────────────────────────────────────────────────────────────────────────
// card used inside the list
// ──────────────────────────────────────────────────────────────────────────
@Composable
fun ArticleCard(
    item: FeedItem,
    onReadMore: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = item.title,
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(item.author, fontSize = 12.sp, color = Color.Gray)
                Text(item.date, fontSize = 12.sp, color = Color.Gray)
            }

            Spacer(Modifier.height(8.dp))

            Text(
                item.excerpt,
                maxLines = 3,
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onReadMore,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4)),
                    shape = RoundedCornerShape(6.dp)
                ) { Text("Read more", color = Color.White, fontSize = 12.sp) }

                Button(
                    onClick = onEdit,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(6.dp)
                ) { Text("Edit", color = Color.White, fontSize = 12.sp) }

                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(6.dp)
                ) { Text("Delete", color = Color.White, fontSize = 12.sp) }
            }
        }
    }
}
