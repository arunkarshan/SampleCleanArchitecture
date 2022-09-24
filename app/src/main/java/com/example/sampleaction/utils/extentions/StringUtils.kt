package com.example.sampleaction.utils.extentions

import androidx.core.util.PatternsCompat


/*********************************************************
 * Class   :  StringUtils
 * Author  :  Arun Nair
 * Created :  16/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

/***************************************
 * Setting Observers
 ***************************************/
fun String?.isValidEmailAddress() : Boolean {
    return !this.isNullOrBlank() &&
            this.length >= 3 &&
            PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

}


/***************************************
 * Setting Observers
 ***************************************/
fun String?.isNotValid(
    minimumCharacters: Int = 5,
    maximumCharacters: Int = Int.MAX_VALUE
): Boolean {
    return this.isNullOrBlank() || this.length < minimumCharacters || this.length > maximumCharacters
}