package com.neupanesushant.imstask.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.neupanesushant.imstask.R
import com.neupanesushant.imstask.data.model.IndiEmployee
import com.neupanesushant.imstask.utils.SystemServiceManagers
import com.neupanesushant.imstask.viewmodel.MainViewModel

class AddUpdateActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    private lateinit var tvTitle: TextView
    private lateinit var etUsername: TextInputEditText
    private lateinit var etAge: TextInputEditText
    private lateinit var etSalary: TextInputEditText
    private lateinit var btnAddUpdateAction: MaterialButton
    private lateinit var btnDelete: MaterialButton

    private var userData: IndiEmployee? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update)

        userData = intent.getParcelableExtra<IndiEmployee>("employee_data")

        initialize()
        setupView()
        setupEventListener()
        setupObserver()
    }

    private fun initialize() {
        tvTitle = findViewById(R.id.tvTitle)
        etUsername = findViewById(R.id.etUsername)
        etAge = findViewById(R.id.etAge)
        etSalary = findViewById(R.id.etSalary)
        btnAddUpdateAction = findViewById(R.id.btnAddUpdateAction)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setupView() {

        if (userData == null) addEmployeeViewSetup() else updateEmployeeViewSetup()

    }

    private fun setupEventListener() {
        btnAddUpdateAction.setOnClickListener {
            val name = etUsername.text.toString()
            val age = etAge.text.toString()
            val salary = etSalary.text.toString()
            val id = userData?.id ?: -1
            val employeeObj = IndiEmployee(id, name, salary.toInt(), age.toInt(), null)

            onInternetConnected {
                if (userData == null) viewModel.add(employeeObj) else viewModel.update(
                    employeeObj
                )
            }
        }
        btnDelete.setOnClickListener {
            onInternetConnected { viewModel.delete(userData!!) }
        }
    }

    private fun setupObserver() {
        viewModel.responseMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addEmployeeViewSetup() {
        tvTitle.text = "Add Employee"
        btnAddUpdateAction.text = "Add"
    }

    @SuppressLint("SetTextI18n")
    private fun updateEmployeeViewSetup() {
        tvTitle.text = "Update Employee"
        btnAddUpdateAction.text = "Update"
        btnDelete.isVisible = true
        userData?.let {
            etUsername.setText(it.name)
            etAge.setText(it.age.toString())
            etSalary.setText(it.salary.toString())
        }
    }

    private fun onInternetConnected(onConnected: () -> Unit) {
        if (SystemServiceManagers.isInternetConnected(this)) {
            onConnected()
        } else {
            Toast.makeText(this, "Please connect to internet", Toast.LENGTH_SHORT).show()
        }
    }

}