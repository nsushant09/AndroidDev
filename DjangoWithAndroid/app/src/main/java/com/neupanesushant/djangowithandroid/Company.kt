package com.neupanesushant.djangowithandroid

class Company(
    val company_id : Int?,
    val name : String,
    val location : String,
    val about : String,
    val type : String,
    val active : Boolean
){
    override fun toString(): String {
        return "Company(company_id='${company_id}', name='$name', location='$location', about='$about', type='$type', active=$active)"
    }
}