package com.neupanesushant.imstask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.neupanesushant.imstask.data.model.IndiEmployee
import com.neupanesushant.imstask.data.model.toIndi
import com.neupanesushant.imstask.data.repo.EmployeeDatabase
import com.neupanesushant.imstask.utils.RetrofitClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var _employees: MutableLiveData<List<IndiEmployee>> = MutableLiveData()
    val employees: LiveData<List<IndiEmployee>> get() = _employees

    val responseMessage: MutableLiveData<String> = MutableLiveData()

    private val database = EmployeeDatabase.getDatabase(application)
    private val employeeDAO = database.employeeDao

    fun getEmployees(isInternetConnected: Boolean) {
        if (isInternetConnected) getEmployeesFromNetwork() else getEmployeesFromDatabase()
    }

    private fun getEmployeesFromNetwork() {
        viewModelScope.launch {
            val response = RetrofitClient.employeeEndpoints.getEmployees()
            _employees.value = response.data
                .map { it.toIndi() }
                .also { cacheEmployee(it) }
        }
    }

    private fun getEmployeesFromDatabase() {
        employeeDAO.all().observeForever { _employees.value = it }
    }

    private suspend fun cacheEmployee(employees: List<IndiEmployee>) = coroutineScope {
        for (employee in employees) {
            employeeDAO.add(employee)
        }
    }

    fun add(indiEmployee: IndiEmployee) = viewModelScope.launch {
        val response = RetrofitClient.employeeEndpoints.addEmployee(indiEmployee)
        responseMessage.value = response.message
    }

    fun update(indiEmployee: IndiEmployee) = viewModelScope.launch {
        val response =
            RetrofitClient.employeeEndpoints.updateEmployee(indiEmployee.id, indiEmployee)
        responseMessage.value = response.message
    }

    fun delete(indiEmployee: IndiEmployee) = viewModelScope.launch {
        val response = RetrofitClient.employeeEndpoints.deleteEmployee(indiEmployee.id)
        responseMessage.value = response.message
    }
}