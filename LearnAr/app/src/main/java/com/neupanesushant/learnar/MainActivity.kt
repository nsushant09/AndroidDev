package com.neupanesushant.learnar

import android.os.Bundle
import com.neupanesushant.learnar.core.BaseActivity
import com.neupanesushant.learnar.databinding.ActivityMainBinding
import com.neupanesushant.learnar.extras.PermissionHandler


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val permissionHandler: PermissionHandler = PermissionHandler(this)
    private val mainFragment = MainFragment()
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun setupViews() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, mainFragment)
            .disallowAddToBackStack().commit()
    }

    override fun setupEventListener() {
        supportFragmentManager
    }

    override fun setupObserver() {
    }

    override fun setupExtras() {
    }
}