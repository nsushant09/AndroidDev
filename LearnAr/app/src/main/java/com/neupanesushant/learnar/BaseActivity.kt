package com.neupanesushant.learnar

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    @get:LayoutRes
    protected abstract val layoutId: Int
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    private fun setup(){
        setupViews()
        setupEventListener()
        setupObserver()
    }
    abstract fun setupViews();
    abstract fun setupEventListener();
    abstract fun setupObserver();


}