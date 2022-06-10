/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.trackmysleepquality.SleepDetailsAdapter
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.*

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

        private val viewModelJob  = Job()


        private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
        private var tonight = MutableLiveData<SleepNight?>()
        val nights = database.getAllNights()

        init{
                initializeTonight()

        }

        private fun initializeTonight(){
                uiScope.launch {
                        tonight.value = getTonightFromDatabase()
                }
        }

        private suspend fun getTonightFromDatabase(): SleepNight? {
                return withContext(Dispatchers.IO){
                        var night = database.getTonight()
                        if(night?.startTimeMilli != night?.endTimeMilli){
                                night = null
                        }
                        night
                }
        }

        // for StartButton
        fun onStartTracking(){
                uiScope.launch{
                        val newNight = SleepNight()
                        insert(newNight)
                        tonight.value = getTonightFromDatabase()
                }
        }

        private suspend fun insert(night : SleepNight){
                withContext(Dispatchers.IO){
                        database.insert(night)
                }
        }

        // for stopButton
        fun onStopTracking(){
                uiScope.launch{
                        val oldNight = tonight.value ?: return@launch
                        oldNight.endTimeMilli = System.currentTimeMillis()
                        update(oldNight)
                }
        }

        private suspend fun update(oldNight : SleepNight){
                withContext(Dispatchers.IO){
                        database.update(oldNight)
                }
        }

        //for clearing the nights
        fun onClear(){
                uiScope.launch{
                        clear()
                        tonight.value = null
                }
        }

        private suspend fun clear(){
                withContext(Dispatchers.IO){
                        database.clear()
                }
        }

        override fun onCleared() {
                super.onCleared()
                viewModelJob.cancel()
        }

}

