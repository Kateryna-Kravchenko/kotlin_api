package com.example.os.kotlin_api.listscreen.di

import com.example.os.kotlin_api.listscreen.*
import com.example.os.kotlin_api.listscreen.ui.ListView
import com.example.os.kotlin_api.listscreen.ui.adapters.OnItemClickListener
import com.example.os.kotlin_api.listscreen.ui.adapters.PostAdapter
import com.example.os.kotlin_api.date.local.LocalDataSource
import com.example.os.kotlin_api.date.local.LocalDataSourceImpl
import com.example.os.kotlin_api.date.remote.RemoteDataSource
import com.example.os.kotlin_api.date.remote.RemoteDataSourceImpl
import com.example.os.kotlin_api.date.remote.http.ApiClient
import com.example.os.kotlin_api.date.remote.http.ApiService
import com.example.os.kotlin_api.date.remote.http.json.Post
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MainListModule(var view: ListView, var clickListener: OnItemClickListener) {


    @Provides
    @Singleton
    fun providesListView(): ListView = view


    @Provides
    @Singleton
    fun providesMainListPresenter(listView: ListView, getSearchInteractor: MainListInteractor): MainListPresenter = MainListPresenterImpl(listView, getSearchInteractor)


    @Provides
    @Singleton
    fun providesMainListInteractor(repository: MainListRepository): MainListInteractor = MainListInteractorImpl(repository)


    @Provides
    @Singleton
    fun providesListMainRepository(mLocalDataSource: LocalDataSource, mRemoteDataSource: RemoteDataSource): MainListRepository = MainListRepositoryImpl(mLocalDataSource, mRemoteDataSource)


    @Provides
    @Singleton
    fun providesRemoteDataSource(mHttpApi: ApiService): RemoteDataSource = RemoteDataSourceImpl(mHttpApi)

    @Provides
    @Singleton
    fun providesLocalDataSource(): LocalDataSource = LocalDataSourceImpl()


    @Provides
    @Singleton
    fun providesApiService(): ApiService = ApiClient().createRetrofitInstance()

    @Provides
    @Singleton
    fun providesPostAdapter(posts: ArrayList<Post>, onItemClickListener: OnItemClickListener): PostAdapter = PostAdapter(posts, onItemClickListener)


    @Provides
    @Singleton
    fun providesEmptyList(): ArrayList<Post> = ArrayList()

    @Provides
    @Singleton
    fun providesOnItemClickListener(): OnItemClickListener = clickListener


}