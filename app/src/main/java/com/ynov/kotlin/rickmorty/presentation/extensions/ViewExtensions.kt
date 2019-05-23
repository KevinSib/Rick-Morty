package com.ynov.kotlin.rickmorty.presentation.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(msg: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, msg, duration).show()
}