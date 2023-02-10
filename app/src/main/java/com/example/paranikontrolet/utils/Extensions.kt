package com.example.paranikontrolet.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun View.showSnackbar(msg: String) {
    Snackbar.make(this, msg, 2500).show()
}
fun View.showSnackbarWithButton(msg: String, navigation:()-> Unit) {
    Snackbar.make(this, msg, 10000).setAction("Ok",View.OnClickListener {
        navigation()
    }).show()
}


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Date.toFormat(dateFormat: String): String {
    return SimpleDateFormat(dateFormat, Locale.getDefault()).format(this)
}