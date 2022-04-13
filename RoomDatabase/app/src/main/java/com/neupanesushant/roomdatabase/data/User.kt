package com.neupanesushant.roomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//this represents an table in database

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val firstName : String,
    val lastName : String,
    val age : Int
        )