package com.neupanesushant.learnar.core

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object AppConfig {

    private var activityMap: Map<String, Class<out AppCompatActivity>> = mapOf()
    private var fragmentMap: Map<String, Class<out Fragment>> = mapOf()

    fun setActivities(activityMap: Map<String, Class<out AppCompatActivity>>) {
        this.activityMap = activityMap
    }

    fun setFragments(fragmentMap: Map<String, Class<out Fragment>>) {
        this.fragmentMap = fragmentMap
    }

    fun getActivity(code: String) = activityMap[code]
    fun getFragment(code: String) = fragmentMap[code]
}