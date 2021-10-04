package com.thecatapi.task

import android.content.Context
import android.widget.Toast

class Utils {
    companion object {
        fun Context.toast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

    }
}