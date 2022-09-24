package com.example.sampleaction.utils.extentions

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.example.sampleaction.models.events.Event
import com.example.sampleaction.models.events.MessageEvent
import com.example.sampleaction.models.navigation.StartActivityModel
import com.example.utils.alerts.AlertDialogModel
import com.example.utils.alerts.showAlertDialog
import com.example.utils.alerts.showToast


/*********************************************************
 * Class   :  AlertExtns
 * Author  :  Arun Nair
 * Created :  15/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/


fun Activity.startActivity(activityModel: StartActivityModel) {
    startActivity(Intent().apply {
        activityModel.activityClass?.let { setClass(this@startActivity, it) }
        action = activityModel.action
        putExtras(activityModel.extras)
        if (activityModel.clearStack)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    })
}

/***************************************
 * Show Exit Confirmation
 ***************************************/
fun <T> Activity.showMessageEvent(event: MessageEvent<T>?) {
    when (event?.eventType) {
        Event.EventType.SHOW_TOAST -> showToast(
            event.message,
            event.duration == Toast.LENGTH_LONG
        )
        Event.EventType.SHOW_SNACKBAR -> showToast(
            event.message,
            event.duration == Toast.LENGTH_LONG
        )
        Event.EventType.SHOW_ALERT_DIALOG -> {
            if (event.message is AlertDialogModel) showAlertDialog(event.message)
        }
        else -> {}
    }
}