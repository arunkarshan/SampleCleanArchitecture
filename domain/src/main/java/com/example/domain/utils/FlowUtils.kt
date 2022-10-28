package com.example.domain.utils

import kotlinx.coroutines.flow.*


/*********************************************************
 * Class   :  FlowUtils
 * Author  :  Arun Nair
 * Created :  27/10/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/


/**
 * Returns a flow containing the results of applying the given [transform] function to each value of the original flow.
 */
public inline fun <T, R> Flow<T>.flatMap(crossinline transform: suspend (value: T) -> Flow<List<R>>): Flow<List<R>> {
    return arrayListOf<Flow<List<R>>>().apply {
        this@flatMap.map {
            add(transform(it))
        }
    }.merge()
}
