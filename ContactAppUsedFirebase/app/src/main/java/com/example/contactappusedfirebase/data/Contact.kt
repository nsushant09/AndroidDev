package com.example.contactappusedfirebase.data

import com.google.firebase.database.Exclude

//this is a data class Contact to store the contact information
data class Contact(
    //the below @get:Exclue will not write the line below it to the firebase i.e the id : String? will not be written
    @get:Exclude
    var id : String? = null,
    var fullName : String? = null,
    var contactNumber : String? = null,
    @get:Exclude
    var isDeleted : Boolean = false
){
    override fun equals(other: Any?): Boolean {
        return if(other is Contact){
            other.id == id
        }else false
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (fullName?.hashCode() ?: 0)
        result = 31 * result + (contactNumber?.hashCode() ?: 0)
        result = 31 * result + isDeleted.hashCode()
        return result
    }
}