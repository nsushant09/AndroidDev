package com.neupanesushant.imstask.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "employee_table")
@Parcelize
data class IndiEmployee(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val salary: Int,
    val age: Int,
    val profileImage: String?
) : Parcelable