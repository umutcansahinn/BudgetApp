package com.example.paranikontrolet.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(msg: String) {
    Snackbar.make(this, msg, 2500).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}