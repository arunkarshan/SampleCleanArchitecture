package com.example.sampleaction.models.navigation

import android.app.Activity
import android.os.Bundle


/*********************************************************
 * Class   :  StartActivityEvent
 * Author  :  Arun Nair
 * Created :  14/3/21
 *******************************************************
 * Purpose :  Event class for launching activity
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
class StartActivityModel(
        val activityClass: Class<out Activity>? = null,
        val action: String? = null,
        val extras: Bundle = Bundle(),
        val clearStack: Boolean = false
)