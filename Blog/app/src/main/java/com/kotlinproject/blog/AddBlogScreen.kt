// ---------- imports ----------
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kotlinproject.blog.viewmodel.MainViewModel
import domain.Utils

// ---------- composable ----------
@Composable
fun AddBlogScreen(
    navController: NavController,
    viewModel: MainViewModel,
    context: Context,
) {
    val feedItem = viewModel.selectedFeedItem
    // ── state ───────────────────────────────────────────────────────────
    var title by remember { mutableStateOf(feedItem?.title ?: "") }
    var desc by remember { mutableStateOf(feedItem?.excerpt ?: "") }

    val isEditMode = feedItem != null
    val maxTitleChars = 100

    // ── UI ──────────────────────────────────────────────────────────────
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        /* Top bar ------------------------------------------------------- */
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Add New Blog here",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(32.dp))

        /* Title label --------------------------------------------------- */
        Text(
            "Blog Title",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = title,
            onValueChange = { if (it.length <= maxTitleChars) title = it },
            placeholder = { Text("Enter blog title here...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF2196F3),
                unfocusedBorderColor = Color(0xFF2196F3)
            ),
            maxLines = 3
        )

        /* Char counter (top‑right of field) ----------------------------- */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                "${title.length}/$maxTitleChars",
                fontSize = 12.sp,
                color = Color.Black
            )
        }

        Spacer(Modifier.height(24.dp))

        /* Description label --------------------------------------------- */
        Text(
            "Blog Description",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = desc,
            onValueChange = { desc = it },
            placeholder = { Text("Enter blog description here...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFF44336),
                unfocusedBorderColor = Color(0xFFF44336)
            ),
            maxLines = 10
        )

        Spacer(Modifier.height(48.dp))

        /* Add / Save button --------------------------------------------- */
        Button(
            onClick = {
                if (isEditMode) {
                    viewModel.updateBlog(feedItem!!.copy(title = title, excerpt = desc), {
                        Utils.showToast(context, "Blog added successfully")
                    }, {
                        Utils.showToast(context, "Could not add blog")
                    })
                } else {
                    viewModel.addBlog(title, desc, {
                        Utils.showToast(context, "Blog updated successfully")
                    }, {
                        Utils.showToast(context, "Could not update blog")
                    })
                }
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text(
                text = if (isEditMode) "Save" else "Add Blog",
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(32.dp))
    }
}
