package com.renatoarg.customviewkotlin.customview

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.renatoarg.customviewkotlin.R

class MyCustomView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    interface CustomViewCallback {
        fun onCallback(text: String) {}
    }

    private var mListener: CustomViewCallback
    private var editText: EditText
    private var button: Button

    init {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_my_custom_view, this, true)
        mListener = context as CustomViewCallback
        editText = view.findViewById(R.id.editText)
        editText.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    mListener.onCallback(editText.text.toString()); true
                }
                else -> {
                    false
                }
            }
        }
        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            mListener.onCallback(editText.text.toString())
        }
    }

    fun cleanEditText() {
        editText.text.clear()
    }

}