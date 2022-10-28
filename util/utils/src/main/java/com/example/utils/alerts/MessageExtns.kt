package com.example.utils.alerts

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/***************************************
 * Show Exit Confirmation
 ***************************************/
fun <T> Fragment.showSnackbar(
    message: T,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val msgTxt = when (message) {
            is String -> message
            is Int -> getString(message)
            else -> throw Exception("Should provide either message or message resId for Snackbar")
        }
        Snackbar.make(requireView(), msgTxt, duration).show()
    } else context?.showToast(message)
}


/***************************************
 * Show Exit Confirmation
 ***************************************/
fun <M> Context.showToast(
    message: M,
    isLong: Boolean = false
) {

    Toast.makeText(
        this,
        when (message) {
            is String -> message
            is Int -> getString(message)
            else -> throw Exception("Should provide either message or message resId for Toast")
        },
        if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    ).show()
}

