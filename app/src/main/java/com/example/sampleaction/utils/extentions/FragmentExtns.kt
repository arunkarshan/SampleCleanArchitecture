package com.example.sampleaction.utils.extentions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


/*********************************************************
 * Class   :  FragmentExtns
 * Author  :  Arun Nair
 * Created :  15/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

/***************************************
 * Setting Observers
 ***************************************/
fun <T> Fragment.observeData(liveData: LiveData<T>, observer: (value: T) -> Unit) {
    liveData.observe(viewLifecycleOwner) { value ->
        observer(value)
    }
}

/***************************************
 * Setting Observers
 ***************************************/
fun <T> Fragment.observeData(liveData: LiveData<T>, vararg functions: (value: T) -> Unit) {
    liveData.observe(viewLifecycleOwner) { value ->
        for (function in functions)
            function(value)
    }
}