package com.neupanesushant.learnar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    protected lateinit var binding: T

    @get:LayoutRes
    protected abstract val layoutId: Int

//    protected val context get() = requireContext()
//    protected val activity get() = requireActivity()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            layoutId,
            container,
            false
        )
        return binding.root
    }

    protected fun onViewCreated() {}
    abstract fun setupView()
    abstract fun setupEventListener()
    abstract fun setupObserver()

}