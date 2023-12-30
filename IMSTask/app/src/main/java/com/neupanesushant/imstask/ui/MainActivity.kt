package com.neupanesushant.imstask.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.neupanesushant.imstask.R
import com.neupanesushant.imstask.data.model.IndiEmployee
import com.neupanesushant.imstask.utils.Constants
import com.neupanesushant.imstask.utils.SystemServiceManagers
import com.neupanesushant.imstask.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val sharedPreferences: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(Constants.APPLICATION_ID, MODE_PRIVATE)
    }

    private lateinit var rvEmployees: RecyclerView
    private lateinit var btnAdd: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        setupView()
        setupEventListener()
        setupObserver()
    }

    private fun initialize() {
        firstAppLaunch()
        viewModel.getEmployees(SystemServiceManagers.isInternetConnected(this))
    }

    private fun setupView() {
        rvEmployees = findViewById(R.id.rvEmployees)
        btnAdd = findViewById(R.id.btnAdd)

        rvEmployees.layoutManager = LinearLayoutManager(this)

    }

    private fun setupEventListener() {
        btnAdd.setOnClickListener { openAddUpdateActivity() }
    }

    private fun setupObserver() {
        viewModel.employees.observe(this) {
            it?.let {
                rvEmployees.adapter = EmployeeAdapter(this, it) {
                    openAddUpdateActivity(it)
                }
            }
        }
    }

    private fun firstAppLaunch() {
        val isFirstLaunch = sharedPreferences.getBoolean(Constants.PREFERENCES_FIRST_LAUNCH, true)
        if (isFirstLaunch) {
            showWelcomeMessage()
            val editor = sharedPreferences.edit()
            editor.putBoolean(Constants.PREFERENCES_FIRST_LAUNCH, false)
            editor.apply()
        }
    }

    private fun showWelcomeMessage() {
        MaterialAlertDialogBuilder(this)
            .setMessage("Welcome to this Android Application for the first launch")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun openAddUpdateActivity(data: IndiEmployee? = null) {
        Intent(this, AddUpdateActivity::class.java).apply {
            data?.let { putExtra("employee_data", it) }
            startActivity(this)
        }
    }
}