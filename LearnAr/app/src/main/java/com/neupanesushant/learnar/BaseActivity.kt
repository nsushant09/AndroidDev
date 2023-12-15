package com.neupanesushant.learnar

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    @get:LayoutRes
    protected abstract val layoutId: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        setup()
    }

    private fun setup() {
        setupViews()
        setupEventListener()
        setupObserver()
        setupExtras()
    }

    abstract fun setupViews();
    abstract fun setupEventListener();
    abstract fun setupObserver();

    protected open fun setupExtras() {}

    protected fun isPermissionGranted(grantResults: IntArray): Boolean {
        return grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
    }
}