package com.neupanesushant.imstask.data.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neupanesushant.imstask.data.model.IndiEmployee

@Database(entities = [IndiEmployee::class], version = 1, exportSchema = false)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract val employeeDao: EmployeeDAO

    companion object {
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null
        fun getDatabase(context: Context): EmployeeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}