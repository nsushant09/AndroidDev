package com.neupanesushant.imstask.data.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.neupanesushant.imstask.data.model.IndiEmployee

@Dao
interface EmployeeDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(employee: IndiEmployee)

    @Update
    suspend fun update(employee: IndiEmployee)

    @Delete
    suspend fun delete(employee: IndiEmployee)

    @Query("SELECT * FROM employee_table")
    fun all(): LiveData<List<IndiEmployee>>
}