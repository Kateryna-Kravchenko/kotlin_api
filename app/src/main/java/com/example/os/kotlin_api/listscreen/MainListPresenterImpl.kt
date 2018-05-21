package com.example.os.kotlin_api.listscreen

import android.util.Log
import com.example.os.kotlin_api.listscreen.ui.ListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainListPresenterImpl( val mListViev: ListView,  val mainListInteractor: MainListInteractor) : MainListPresenter {
    override fun getPosts() {
        mainListInteractor.execute().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    mListViev.showPosts(result)
                }, { error ->
                    Log.e(TAG,error.toString()+ error.message)
                })
    }

    companion object {
        const val TAG: String = "MainListPresenterImpl"
    }
}