package com.kotlinproject.blog.viewmodel

import FeedItem
import FeedItemDAO
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import data.FirebaseInstance
import data.User
import domain.RegisterAndLogin
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainViewModel : ViewModel() {

    private val registerAndLogin = RegisterAndLogin()

    var selectedFeedItem by mutableStateOf<FeedItem?>(null)
        private set

    var currentUser by mutableStateOf<User?>(null)
        private set

    var myFeedItems by mutableStateOf<List<FeedItem>>(emptyList())
        private set

    var feedItems by mutableStateOf<List<FeedItem>>(emptyList())
        private set

    var bookmarkedFeedItems by mutableStateOf<List<FeedItem>>(emptyList())
        private set


    fun selectFeedItem(item: FeedItem) {
        selectedFeedItem = item
    }

    fun resetFeedItem() {
        selectedFeedItem = null
    }

    fun login(email: String, password: String, onResult: (FirebaseUser?) -> Unit) {
        viewModelScope.launch {
            FirebaseInstance.firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    onResult(it.user)
                }.addOnFailureListener {  }
        }
    }

    fun register(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: (Int) -> Unit
    ) {
        viewModelScope.launch {
            FirebaseInstance.firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    addUser(name, email, phone, result, onSuccess, onFailure)
                }.addOnFailureListener { onFailure(1) }
        }
    }

    private fun addUser(
        name: String,
        email: String,
        phone: String,
        result: AuthResult,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: (Int) -> Unit
    ) {
        val user = User(
            result.user?.uid ?: ":)", name, email, phone

        )
        viewModelScope.launch {
            FirebaseInstance.firebaseDatabase.getReference("/users/${FirebaseInstance.firebaseAuth.uid}")
                .setValue(user).addOnSuccessListener { onSuccess(result.user!!) }
                .addOnFailureListener { onFailure(2) }
        }
    }

    fun getUserFromId() {
        val uid = FirebaseInstance.firebaseAuth.uid ?: return

        val userRef = FirebaseInstance.firebaseDatabase.getReference("/users/$uid")

        viewModelScope.launch {
            userRef.get().addOnSuccessListener { snapshot ->
                currentUser = snapshot.getValue(User::class.java)
            }
        }
    }


    fun addBlog(
        title: String,
        excerpt: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        //  ❶  Create a new key under /blogs
        val blogsRef = FirebaseInstance.firebaseDatabase.getReference("/blogs")
        val blogId = blogsRef.push().key ?: return onFailure()      // 1 = could not create key

        val feedItem = FeedItemDAO(
            id = blogId,
            title = title,
            excerpt = excerpt,
            author_id = FirebaseInstance.userId,
            date = getCurrentDate(),
        )

        viewModelScope.launch {
            blogsRef.child(blogId)
                .setValue(feedItem)
                .addOnSuccessListener {
                    onSuccess()
                    getAllBlogs()
                }
                .addOnFailureListener { onFailure() }                 // 2 = write failed
        }
    }

    fun updateBlog(
        blog: FeedItem,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val blogId = blog.id
        if (blogId.isBlank()) return onFailure()                      // 3 =‑no id to update

        val updated = FeedItemDAO(
            id = blog.id,
            title = blog.title,
            excerpt = blog.excerpt,
            author_id = blog.author,
            date = blog.date
        )
        viewModelScope.launch {
            FirebaseInstance.firebaseDatabase
                .getReference("/blogs/$blogId")
                .setValue(updated)
                .addOnSuccessListener {
                    onSuccess()
                    getAllBlogs()
                }
                .addOnFailureListener { onFailure() }                 // 4 = update failed
        }
    }


    fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)
        val formattedDate = currentDate.format(formatter)
        return formattedDate
    }

    fun deleteFeedItem(
        id: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val blogRef = FirebaseInstance.firebaseDatabase.getReference("/blogs/$id")

        viewModelScope.launch {
            blogRef
                .removeValue()
                .addOnSuccessListener {
                    onSuccess()
                    getAllBlogs()
                }
                .addOnFailureListener { onFailure() }
        }
    }

    fun addBookmark(
        feedId: String,
    ) {
        val userId = FirebaseInstance.firebaseAuth.uid ?: return
        val bookmarkRef =
            FirebaseInstance.firebaseDatabase.getReference("/bookmarks/$userId/$feedId")

        viewModelScope.launch {
            bookmarkRef.setValue(true)
                .addOnSuccessListener {
                    getAllBookmarks()
                    getAllBlogs()
                }
        }
    }

    fun removeBookmark(
        feedId: String,
    ) {
        val userId = FirebaseInstance.firebaseAuth.uid ?: return
        val bookmarkRef =
            FirebaseInstance.firebaseDatabase.getReference("/bookmarks/$userId/$feedId")

        viewModelScope.launch {
            bookmarkRef.removeValue()
                .addOnSuccessListener {
                    getAllBookmarks()
                    getAllBlogs()
                }
        }
    }


    fun getAllBookmarks() {
        val userId = FirebaseInstance.firebaseAuth.uid ?: return
        val bookmarksRef = FirebaseInstance.firebaseDatabase.getReference("/bookmarks/$userId")

        viewModelScope.launch {
            bookmarksRef.get()
                .addOnSuccessListener { snapshot ->
                    val bookmarkIds = snapshot.children.mapNotNull { it.key }

                    val bookmarkedList = feedItems.filter { it.id in bookmarkIds }
                        .map { it.copy(isBookmarked = true) }

                    bookmarkedFeedItems = bookmarkedList
                }
                .addOnFailureListener {
                    // Handle failure if needed
                }
        }
    }


    fun getAllBlogs() {
        val blogsRef = FirebaseInstance.firebaseDatabase.getReference("/blogs")
        val userId = FirebaseInstance.firebaseAuth.uid       // may be null if not logged‑in
        val bookmarksRef = userId?.let {
            FirebaseInstance.firebaseDatabase.getReference("/bookmarks/$it")
        }

        viewModelScope.launch {
            // ── 1 ▸ fetch *all* blogs
            blogsRef.get()
                .addOnSuccessListener { blogsSnap ->
                    val allBlogDao = blogsSnap.children
                        .mapNotNull { it.getValue(FeedItemDAO::class.java) }

                    // Helper that converts DAO ► FeedItem using a supplied bookmark‑id set
                    fun buildLists(bookmarkedIds: Set<String>) {
                        val allFeed = allBlogDao.map { dao ->
                            FeedItem(
                                id = dao.id,
                                title = dao.title,
                                excerpt = dao.excerpt,
                                author = dao.author_id,
                                date = dao.date,
                                isBookmarked = bookmarkedIds.contains(dao.id)
                            )
                        }

                        feedItems = allFeed           // every post
                        bookmarkedFeedItems = allFeed.filter { it.isBookmarked }
                        myFeedItems = allFeed.filter { it.author == FirebaseInstance.userId }
                    }

                    // ── 2 ▸ if logged‑in, fetch bookmarks; else skip
                    if (bookmarksRef == null) {
                        buildLists(emptySet())
                        return@addOnSuccessListener
                    }

                    bookmarksRef.get()
                        .addOnSuccessListener { bmSnap ->
                            val bookmarkedIds = bmSnap.children
                                .mapNotNull { it.key }
                                .toSet()
                            buildLists(bookmarkedIds)
                        }
                        .addOnFailureListener {
                            // could not load bookmarks ➜ still show all blogs
                            buildLists(emptySet())
                        }
                }
                .addOnFailureListener {
                    // handle blog‑load error (toast, log, etc.)
                }
        }
    }


}