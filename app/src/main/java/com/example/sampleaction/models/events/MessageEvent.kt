package com.example.sampleaction.models.events

import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


/*********************************************************
 * Class   :  MessageEvent
 * Author  :  Arun Nair
 * Created :  14/3/21
 *******************************************************
 * Purpose :  Event class for Message (Toast, snackbar...)
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
class MessageEvent<T>(
    val message: T,
    val duration: Int,
    override val eventType: EventType = EventType.SHOW_TOAST
) : Event<T>(message, eventType) {

    /***************************************
     *  Set Toast Error
     ***************************************/
    companion object {
        /***************************************
         *  Set Toast Error
         ***************************************/
        fun getSnackBarEvent(message: Any? = null,
                             duration: Int = Snackbar.LENGTH_SHORT
        ): MessageEvent<*> {
            return message?.let{
                MessageEvent(message, duration, EventType.SHOW_SNACKBAR)
            } ?: throw Exception("Should provide either String or ResId for MessageEvent")
        }

        /***************************************
         *  Set Toast Error
         ***************************************/
        fun getToastEvent(message: Any? = null,
                          duration: Int = Toast.LENGTH_SHORT
        ): MessageEvent<*> {
            return message?.let{
                MessageEvent(message, duration, EventType.SHOW_TOAST)
            } ?: throw Exception("Should provide either String or ResId for MessageEvent")
        }

        /***************************************
         *  Set Toast Error
         ***************************************/
        fun getAlertDialog(message: com.example.utils.alerts.AlertDialogModel): MessageEvent<*> {
            return message.let{
                MessageEvent(message, 0, EventType.SHOW_ALERT_DIALOG)
            }
        }
    }
}