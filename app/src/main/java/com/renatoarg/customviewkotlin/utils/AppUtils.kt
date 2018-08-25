package com.renatoarg.customviewkotlin.utils

import android.content.Context
import android.database.CursorWindow
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class AppUtils {
    companion object {
        fun hideKeyboard(context: Context, v: View) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}