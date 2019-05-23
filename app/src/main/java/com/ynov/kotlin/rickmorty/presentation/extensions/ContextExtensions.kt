package com.ynov.kotlin.rickmorty.presentation.extensions

import android.app.AlertDialog
import android.content.Context


fun Context.showMessage(title: String, message: String) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton(android.R.string.yes) { dialog, _ ->
        dialog.dismiss()
    }
    builder.show()
}