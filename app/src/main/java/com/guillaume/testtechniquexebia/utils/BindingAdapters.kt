package com.guillaume.testtechniquexebia.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.guillaume.testtechniquexebia.utils.extension.getParentActivity

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View,  visibility: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView,  text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("mutableInt")
fun setMutableInt(view: TextView,  int: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && int != null) {
        int.observe(parentActivity, Observer { value -> view.text = (value?:"").toString() })
    }
}
/**
@BindingAdapter("mutableArrayString")
fun setMutableArrayString(view: TextView,  arrayString: MutableLiveData<Array<String>>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && arrayString != null) {
        arrayString.observe(parentActivity, Observer { value -> view.text = ((value?:"").toString()) })
    }
}

*/