package com.renatoarg.customviewkotlin

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.renatoarg.customviewkotlin.customview.MyCustomView.CustomViewCallback
import com.renatoarg.customviewkotlin.utils.AppUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_my_custom_view.view.*

class MainActivity : AppCompatActivity(), CustomViewCallback {

    val mStrings: MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = recyclerView
        recyclerView.adapter = MyAdapter(this@MainActivity, mStrings)
    }

    override fun onCallback(text: String) {
        super.onCallback(text)
        AppUtils.hideKeyboard(this@MainActivity, myCustomView)
        myCustomView.cleanEditText()
        mStrings.add(text)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    class MyAdapter(private var context: Context, private val strings : List<String>) : Adapter<MyAdapter.ViewHolder>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.row_my_custom_view, p0, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return strings.size
        }

        override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
            p0.string.text = strings[p1]
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val string = itemView.tv_string!!
        }
    }

}
