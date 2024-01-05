package com.neupanesushant.learnar.arfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neupanesushant.learnar.R
import com.neupanesushant.learnar.core.BaseFragment
import com.neupanesushant.learnar.databinding.FragmentTestBinding

class TestFragment : BaseFragment<FragmentTestBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_test


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun setupViews() {
    }

    override fun setupEventListener() {
    }

    override fun setupObserver() {
    }

}