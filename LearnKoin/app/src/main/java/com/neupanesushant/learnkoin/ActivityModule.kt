package com.neupanesushant.learnkoin

import org.koin.core.qualifier.named
import org.koin.dsl.module

val activityModule = module{
    scope<MainActivity>{
        //provide qualifier name to avoid datatype conflict
        scoped(qualifier = named("hello")){"Hello"}
        scoped(qualifier = named("bye")){"Bye"}
    }
}