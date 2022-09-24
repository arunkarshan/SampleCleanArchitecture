package com.example.domain.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn


/*********************************************************
 * Class   :  ExtensionUtils
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/


fun <T> T.asFlow(dispatcher: CoroutineDispatcher): Flow<T> {
    return flowOf(this).flowOn(dispatcher)
}

fun <T> List<T>.combineBy(predicate: (T) -> Boolean, selector: (T) -> Int): Int {
    return filter { predicate.invoke(it) }.sumOf { selector.invoke(it) }
}

fun <T> List<T>.combineBy(predicate: (T) -> Boolean, selector: (T) -> Long): Long {
    return filter { predicate.invoke(it) }.sumOf { selector.invoke(it) }
}

