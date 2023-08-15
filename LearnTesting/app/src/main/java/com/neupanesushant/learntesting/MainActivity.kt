package com.neupanesushant.learntesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neupanesushant.learntesting.databinding.ActivityMainBinding

// A software is not complete without test cases
// Unit test : They are not the only test in the app

/**
 * UI TEST : aka end-to-end test
 * Tests that check if many or all components of your app work together well and if the UI looks like it should
 * Must always run on the android emulator
 * Covers 10 percent
 *
 * Integration Tests : Tests how two components of our app work together (e.g. Fragment and ViewModel relation)
 * Different than Integrated test : i.e. test that relies on android components
 * Covers 20 percent
 * When an application / test requires app components like component / resources those are integrated tests
 *
 * Unit tests :
 * Testing function of a class
 * Covers 70 percent
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}