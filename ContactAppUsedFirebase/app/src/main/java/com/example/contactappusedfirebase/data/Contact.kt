package com.example.contactappusedfirebase.data

import com.google.firebase.database.Exclude

data class Contact(
    @get:Exclude
    var id : String? = null,
    var fullName : String? = null,
    var contactNumber : String? = null
)