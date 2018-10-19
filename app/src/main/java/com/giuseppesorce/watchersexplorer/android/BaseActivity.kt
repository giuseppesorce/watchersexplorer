package com.giuseppesorce.watchersexplorer.android

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem


abstract class BaseActivity : AppCompatActivity() {



    /**
     * Closure invoked when the home button on the toolbar has been pressed.
     */

    val application: AppAplication
        get() = applicationContext as AppAplication

    override fun onCreate(savedInstanceState: Bundle?) {
        onInject()
        super.onCreate(savedInstanceState)

    }


    @CallSuper
    open fun onInject() {
        application.applicationComponent.inject(this)
    }



//TODO DA VEDERRE
//    /**
//     * Shows the keyboard using the given view which has focus.
//     */
//    fun showKeyboard(view: View?) {
//        view?.let {
//            val service = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
//            service?.showSoftInput(it, 0)
//        }
//    }
//
//    /**
//     * Hides the keyboard using the given view for obtaining the current window.
//     */
//    fun hideKeyboard(view: View?) {
//        view?.windowToken?.let {
//            val service = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
//            service?.hideSoftInputFromWindow(it, 0)
//        }
//    }
}