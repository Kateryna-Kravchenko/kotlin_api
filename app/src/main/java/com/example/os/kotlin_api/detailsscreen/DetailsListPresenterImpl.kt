package com.example.os.kotlin_api.detailsscreen

import android.util.Log
import com.example.os.kotlin_api.date.remote.http.json.User
import com.example.os.kotlin_api.detailsscreen.ui.DetailsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsListPresenterImpl(val mDetailsView: DetailsView, val mDetailsListInteractor: DetailsListInteractor) : DetailsListPresenter {

    companion object {
        const val TAG: String = "DetailsListPresenterImpl"
    }
    override fun getUser(userId: Int) {
        mDetailsView

        mDetailsListInteractor.executeUser(userId)?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())?.subscribe({ result ->
            mDetailsView.showUserData(result as User)
        }, { error ->
            Log.e(TAG, error.toString() )
        })
    }

    override fun getComments(postId: Int) {

        mDetailsListInteractor.executeComments(postId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    mDetailsView.showComments(result)
                }, { error ->
                    Log.e(TAG, error.toString())
                })    }



}