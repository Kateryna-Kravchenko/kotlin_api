package com.example.os.kotlin_api

import android.app.Application
import com.example.os.kotlin_api.detailsscreen.di.DaggerDetailsListComponent
import com.example.os.kotlin_api.detailsscreen.di.DetailsListComponent
import com.example.os.kotlin_api.detailsscreen.di.DetailsListModule
import com.example.os.kotlin_api.detailsscreen.ui.DetailsView
import com.example.os.kotlin_api.listscreen.di.DaggerMainListComponent
import com.example.os.kotlin_api.listscreen.di.MainListComponent
import com.example.os.kotlin_api.listscreen.di.MainListModule
import com.example.os.kotlin_api.listscreen.ui.ListView
import com.example.os.kotlin_api.listscreen.ui.adapters.OnItemClickListener

class PostApp : Application() {
    fun getMainListComponent(view: ListView, onItemClickListener: OnItemClickListener): MainListComponent {
        return DaggerMainListComponent
                .builder()
                .mainListModule(MainListModule(view, onItemClickListener))
                .build()
    }

    fun getDetailsListComponent(view: DetailsView): DetailsListComponent {
        return DaggerDetailsListComponent
                .builder()
                .detailsListModule(DetailsListModule(view))
                .build()
    }
}