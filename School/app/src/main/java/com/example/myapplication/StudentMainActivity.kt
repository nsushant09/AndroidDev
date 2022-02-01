package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.studentfragments.StudentGallaryFragment
import com.example.myapplication.studentfragments.StudentHomeFragment
import com.example.myapplication.studentfragments.StudentMenuFragment
import com.example.myapplication.studentfragments.StudentSettingsFragment
import com.google.android.material.navigation.NavigationBarView

class StudentMainActivity : AppCompatActivity() {

    private val homeFragment = StudentHomeFragment()
    private val settingsFragment  = StudentSettingsFragment()
    private val gallaryFragment = StudentGallaryFragment()
    private val menuFragment = StudentMenuFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main)
        replaceFragment(homeFragment)

        val navigation_bar : NavigationBarView = findViewById(R.id.navigation_bar)
        navigation_bar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.settings -> replaceFragment(settingsFragment)
                R.id.menu -> replaceFragment(menuFragment)
                R.id.gallary -> replaceFragment(gallaryFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container,fragment)
            fragmentTransaction.commit()
        }
    }
}