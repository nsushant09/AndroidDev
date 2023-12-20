package com.neupanesushant.learnar.appcore

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.neupanesushant.learnar.MainActivity
import com.neupanesushant.learnar.MainFragment
import com.neupanesushant.learnar.arfragments.AugmentedImagesFragment
import com.neupanesushant.learnar.arfragments.BasicAugmentationFragment
import com.neupanesushant.learnar.arfragments.BuildModelFragment
import com.neupanesushant.learnar.core.AppConfig

class BaseApplication : Application() {
    private val activityMap = mutableMapOf<String, Class<out AppCompatActivity>>()
    private val fragmentMap = mutableMapOf<String, Class<out Fragment>>()

    override fun onCreate() {
        super.onCreate()

        setupActivities()
        setupFragments()
        setOnAppConfig()
    }

    private fun setupActivities() {
        activityMap.apply {
            put(RouteConfig.MAIN_ACTIVITY, MainActivity::class.java)
        }
    }

    private fun setupFragments() {
        fragmentMap.apply {
            put(RouteConfig.MAIN_FRAGMENT, MainFragment::class.java)
            put(RouteConfig.BASIC_AUGMENTATION_FRAGMENT, BasicAugmentationFragment::class.java)
            put(RouteConfig.BUILD_MODEL_FRAGMENT, BuildModelFragment::class.java)
            put(RouteConfig.AUGMENTED_IMAGES_FRAGMENT, AugmentedImagesFragment::class.java)
        }
    }

    private fun setOnAppConfig() {
        AppConfig.setActivities(activityMap)
        AppConfig.setFragments(fragmentMap)
    }
}