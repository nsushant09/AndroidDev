package com.example.retrofit.koinmodules

import com.example.retrofit.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun vmModule() = module {
//    viewModel {
//        MainViewModel(get())
//    }

    viewModel{MainViewModel()}

}