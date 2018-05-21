package com.example.os.kotlin_api.listscreen.di

import com.example.os.kotlin_api.listscreen.MainListPresenter
import com.example.os.kotlin_api.listscreen.ui.ListActivity
import com.example.os.kotlin_api.listscreen.ui.adapters.PostAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MainListModule::class))
interface MainListComponent {
    fun inject(listActivity: ListActivity)
    fun  postAdapter(): PostAdapter
    fun  mainListPresenter(): MainListPresenter
}