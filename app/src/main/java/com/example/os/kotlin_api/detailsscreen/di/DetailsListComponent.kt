package com.example.os.kotlin_api.detailsscreen.di

import com.example.os.kotlin_api.detailsscreen.DetailsListPresenter
import com.example.os.kotlin_api.detailsscreen.ui.DetailsActivity
import com.example.os.kotlin_api.detailsscreen.ui.adapters.CommentAdapter
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(DetailsListModule::class))
interface DetailsListComponent {
    fun inject(detailsActivity: DetailsActivity)
    fun commentAdapter(): CommentAdapter
    fun detailsListPresenter(): DetailsListPresenter
}