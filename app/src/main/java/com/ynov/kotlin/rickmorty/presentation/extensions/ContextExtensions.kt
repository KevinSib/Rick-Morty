package com.ynov.kotlin.rickmorty.presentation.extensions

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.widget.Toast
import android.app.ProgressDialog



fun Context.showLoading(): ProgressDialog {
    val progressDialog = ProgressDialog(this)
    progressDialog.show()
    return progressDialog
}

fun Context.showMessage(title: String, message: String) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
        dialog.dismiss()
    }
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}