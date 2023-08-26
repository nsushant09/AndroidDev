package com.neupanesushant.roomdb

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * SmallTest : Unit Test
 * MediumTest : Instrumented Test
 * LargeTest : UI Test
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    private lateinit var database: UserDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext<Context>(),
                UserDatabase::class.java
            )
            .allowMainThreadQueries()
            .build()

        userDao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertUser() = runTest {
        val user = User(1, "Sushant", "Neupane", 20)
        userDao.addUser(user)
        userDao.readAllData().observeForever {
            assertThat(it?.contains(user)).isTrue()
        }
    }


}