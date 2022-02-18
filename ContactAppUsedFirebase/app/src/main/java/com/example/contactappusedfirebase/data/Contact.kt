package com.example.contactappusedfirebase.data

import com.google.firebase.database.Exclude

//this is a data class Contact to store the contact information
data class Contact(
    //the below @get:Exclue will not write the line below it to the firebase i.e the id : String? will not be written
    @get:Exclude
    var id : String? = null,
    var fullName : String? = null,
    var contactNumber : String? = null
)