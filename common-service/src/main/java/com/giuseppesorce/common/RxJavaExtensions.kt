package com.giuseppesorce.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Giuseppe Sorce
 */
fun Disposable.addDisposableTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}