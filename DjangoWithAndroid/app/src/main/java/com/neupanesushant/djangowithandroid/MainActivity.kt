package com.neupanesushant.djangowithandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.neupanesushant.djangowithandroid.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val endpoints: Endpoints by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupEventListener()
        setupObserver()
    }

    private fun setupView() {
        performRequest()
    }

    private fun setupEventListener() {
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val location = binding.etLocation.text.toString()
            val about = binding.etAbout.text.toString()
            val type = binding.etType.text.toString()
            val isActive = binding.switchActive.isChecked

            val company = Company(null, name, location, about, type, isActive)

            lifecycleScope.launch {
                try {
                    val response = endpoints.addCompany(company)
                    if (response.isSuccessful) {
                        Log.i("Main", "${response.body()}")
                    } else {
                        Log.i("Main", "${response.errorBody()}")
                    }
                } catch (e: java.lang.Exception) {
                    Log.i("Main", "Exception")
                }
            }
        }

        binding.btnDeleteEmployee.setOnClickListener {
            lifecycleScope.launch {
                try {
                    delete(binding.etDeleteEmployee.text.toString().toInt())
                } catch (e: java.lang.Exception) {
                    Toast.makeText(this@MainActivity, "Enter valid id", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupObserver() {
    }

    private fun performRequest() {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val list = fetchData()
                print(list)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun delete(id: Int) {
        val response = endpoints.deleteEmployee(id)
        if(response.isSuccessful){
            Toast.makeText(this@MainActivity, "Employee delete", Toast.LENGTH_SHORT).show()
            binding.etDeleteEmployee.setText("")
        }
    }

    private suspend fun fetchData(): List<Company> {
        return endpoints.getAllCompanies()
    }
}