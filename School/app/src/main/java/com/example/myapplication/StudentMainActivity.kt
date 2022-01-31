package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.studentfragments.StudentProfileFragment
import com.example.myapplication.studentfragments.StudentSettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class StudentMainActivity : AppCompatActivity() {

    private val profileFragment = StudentProfileFragment()
    private val settingsFragment  = StudentSettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_main)
        replaceFragment(profileFragment)

        val navigation_bar : NavigationBarView = findViewById(R.id.navigation_bar)
        navigation_bar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.profile -> replaceFragment(profileFragment)
                R.id.settings -> replaceFragment(settingsFragment)
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