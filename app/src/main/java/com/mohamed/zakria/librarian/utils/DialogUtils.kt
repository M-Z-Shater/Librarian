package com.mohamed.zakria.librarian.utils

import android.app.AlertDialog
import android.content.Context
import com.mohamed.zakria.R

fun createAndShowDialog(context: Context,
    title: String,
    message: String,
    onPositiveAction: () -> Unit,
    onNegativeAction: () -> Unit = {}) {
  val dialog = AlertDialog.Builder(context)
      .setTitle(title)
      .setMessage(message)
      .setPositiveButton(R.string.yes) { dialog, _ ->
        onPositiveAction()
        dialog.dismiss()
      }
      .setNegativeButton(R.string.cancel) { dialog, _ ->
        onNegativeAction()
        dialog.dismiss()
      }
      .create()

  dialog.show()
}