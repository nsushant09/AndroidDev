package com.neupanesushant.learnar.core

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    private lateinit var binding: T

    @get:LayoutRes
    protected abstract val layoutId: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        setup()
    }

    private fun setup() {
        initialize()
        setupViews()
        setupEventListener()
        setupObserver()
        setupExtras()
    }

    abstract fun setupViews()
    abstract fun setupEventListener()
    abstract fun setupObserver()

    protected open fun initialize() {}
    protected open fun setupExtras() {}

    protected fun isPermissionGranted(grantResults: IntArray): Boolean {
        return grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    fun getActivityBinding(): T = binding
}