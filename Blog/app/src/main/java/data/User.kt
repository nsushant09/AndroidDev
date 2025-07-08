package data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val uid : String? = null,
    val name : String? = null,
    val email:String? = null,
    val phoneNumber:String? = null
) : Parcelable
