package com.noban.appschedular.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DataBindingAdapters {

    @JvmStatic
    @BindingAdapter("formatDate")
    fun setDate(textView: TextView,timeStamp: Long) {
        var outputDate: String? = ""
        try {
            val formatter = SimpleDateFormat("MMM dd, yyyy hh:mm a")
            outputDate =  formatter.format(Date(timeStamp))
            textView.text = outputDate
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

}