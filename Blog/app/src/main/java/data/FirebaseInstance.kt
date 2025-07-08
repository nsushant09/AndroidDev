package data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage

object FirebaseInstance {
    val firebaseAuth: FirebaseAuth get() = FirebaseAuth.getInstance()

    val firebaseStorage: FirebaseStorage get() = FirebaseStorage.getInstance()

    val firebaseDatabase : FirebaseDatabase get()  = FirebaseDatabase.getInstance("https://kotlin-blog-963b3-default-rtdb.asia-southeast1.firebasedatabase.app")

    val firebaseCloudMessage get() = FirebaseMessaging.getInstance()

    private val firebaseUser get() = firebaseAuth.currentUser!!

    val userId get() = firebaseUser.uid

}