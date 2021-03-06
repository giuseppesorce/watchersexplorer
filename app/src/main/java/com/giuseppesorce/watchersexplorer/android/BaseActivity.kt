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
    var onHomeBackButtonPressed: (() -> Unit)? = null

    val application: AppAplication
        get() = applicationContext as AppAplication

    override fun onCreate(savedInstanceState: Bundle?) {
        onInject()
        super.onCreate(savedInstanceState)

        // As required, fix the orientation to portrait.
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

    }

    @CallSuper
    open fun onInject() {
        application.applicationComponent.inject(this)
    }

    @CallSuper
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onHomeBackButtonPressed?.invoke()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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