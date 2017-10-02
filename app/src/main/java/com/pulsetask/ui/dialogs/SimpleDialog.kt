package com.pulsetask.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import com.pulsetask.R

/**
 * Created by Ayo on 18/07/2017.
 */

object SimpleDialog {

    private lateinit var dialog: AlertDialog

    fun show(context: Context, title: String?, message: String?, cancelable: Boolean) {
        val builder = AlertDialog.Builder(context)

        if (message != null)
            builder.setMessage(message)

        if (title != null)
            builder.setTitle(title)

        builder.setCancelable(cancelable)
        builder.setPositiveButton(context.getString(R.string.ok_text)) { dialog, _ -> dialog.cancel() }

        dialog = builder.create()
        dialog.show()
    }
}
