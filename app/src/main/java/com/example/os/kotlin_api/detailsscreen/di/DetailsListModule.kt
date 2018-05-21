package com.example.os.kotlin_api.detailsscreen.di

import com.example.os.kotlin_api.date.local.LocalDataSource
import com.example.os.kotlin_api.date.local.LocalDataSourceImpl
import com.example.os.kotlin_api.date.remote.RemoteDataSource
import com.example.os.kotlin_api.date.remote.RemoteDataSourceImpl
import com.example.os.kotlin_api.date.remote.http.ApiClient
import com.example.os.kotlin_api.date.remote.http.ApiService
import com.example.os.kotlin_api.date.remote.http.json.Comment
import com.example.os.kotlin_api.detailsscreen.*
import com.example.os.kotlin_api.detailsscreen.ui.DetailsView
import com.example.os.kotlin_api.detailsscreen.ui.adapters.CommentAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailsListModule(var view: DetailsView) {


    @Provides
    @Singleton
    fun providesDetailsView(): DetailsView = view


    @Provides
    @Singleton
    fun providesDetailsListPresenter(detailsView: DetailsView, detailsListInteractor: DetailsListInteractor): DetailsListPresenter = DetailsListPresenterImpl(detailsView, detailsListInteractor)


    @Provides
    @Singleton
    fun providesDetailsListInteractor(repository: DetailsListRepository): DetailsListInteractor = DetailsListInteractorImpl(repository)


    @Provides
    @Singleton
    fun providesDetailsListRepository(mLocalDataSource: LocalDataSource, mRemoteDataSource: RemoteDataSource): DetailsListRepository = DetailsListRepositoryImpl(mLocalDataSource, mRemoteDataSource)


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
    fun providesCommentAdapter(posts: ArrayList<Comment>): CommentAdapter = CommentAdapter(posts)


    @Provides
    @Singleton
    fun providesEmptyList(): ArrayList<Comment> = ArrayList()


}