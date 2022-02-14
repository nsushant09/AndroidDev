package com.example.bottomnavigationpractice

import android.accounts.Account
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnavigationpractice.fragments.AccountFragment
import com.example.bottomnavigationpractice.fragments.HomeFragment
import com.example.bottomnavigationpractice.fragments.MessageFragment
import com.example.bottomnavigationpractice.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*


//Build this app to have clear concept about fragments/ frame layout and specifically practise how to build a bottom navigation bar
private val accountFragment = AccountFragment()
private val homeFragment = HomeFragment()
private val messageFragment = MessageFragment()
private val settingsFragment = SettingsFragment()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)

        bottom_navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.account -> replaceFragment(accountFragment)
                R.id.message -> replaceFragment(messageFragment)
                R.id.settings -> replaceFragment(settingsFragment)
            }
            true
        }
    }

    @SuppressLint("PrivateResource")
    fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val fragmentTransition = supportFragmentManager.beginTransaction()
//            fragmentTransition.setCustomAnimations(
//                androidx.appcompat.R.anim.abc_popup_enter,
//                androidx.appcompat.R.anim.abc_popup_exit
//               )
            fragmentTransition.replace(R.id.fragment_container,fragment)
            fragmentTransition.commit()
        }
    }
}