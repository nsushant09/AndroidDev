package domain

import android.net.Uri
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.AuthResult
import data.FirebaseInstance
import data.User
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class RegisterAndLogin {

    suspend fun login(email: String, password: String): AuthResult = coroutineScope {
        val job = async {
            FirebaseInstance.firebaseAuth.signInWithEmailAndPassword(email, password)
        }
        await(job.await())
    }


    suspend fun createNewUser(email: String, password: String): AuthResult = coroutineScope {
        val job =
            async { FirebaseInstance.firebaseAuth.createUserWithEmailAndPassword(email, password) }
        await(job.await())
    }

    suspend fun addUser(
        name:String,
        phoneNumber:String,
        email:String,
    ): Boolean {
        return withContext(Dispatchers.IO) {
            val user = User(
                FirebaseInstance.firebaseAuth.uid,
                name,
                email,
                phoneNumber
            )

            val deferred = CompletableDeferred<Boolean>()
            FirebaseInstance.firebaseDatabase.getReference("/users/${FirebaseInstance.firebaseAuth.uid}")
                .setValue(user)
                .addOnSuccessListener {
                    deferred.complete(true)
                }

            deferred.await()
        }
    }
}