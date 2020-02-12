package com.afra55.httpforus.h

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 * @author Afra55
 * @date 2019-06-19
 * A smile is the best business card.
 */
open class  WeHelpRxPresenter {

    private var compositeDisposable:CompositeDisposable? = null

    protected fun <T> addSubscriber(disposableObserver: DisposableObserver<T>): DisposableObserver<T> {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable!!.add(disposableObserver)
        return disposableObserver
    }

    fun unSubscriber() {
        if (compositeDisposable != null) {
            compositeDisposable!!.clear()
        }
    }

}