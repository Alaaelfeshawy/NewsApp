package com.example.newsapp.ui.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import com.example.domain.model.home.Article
import com.example.newsapp.R
import com.example.newsapp.databinding.LatestNewsItemBinding
import com.example.newsapp.databinding.TopNewsItemBinding
import com.example.newsapp.ui.base.BaseViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object Util {
    var alert: AlertDialog? = null

    fun showLoading(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setView(R.layout.progress_bar_dialog)
        builder.setCancelable(false)
        alert = builder.create()
        if (alert?.window != null) {
            alert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        alert?.show()
    }

    fun dismissLoading() {
        if (alert != null) {
            alert?.dismiss()
        }
    }

    fun covertDate(date:String) : String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateTime: LocalDateTime = LocalDateTime.parse(date, formatter)
        val formatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy")
        return dateTime.format(formatter2)
    }

    fun <T : ViewDataBinding> updateBookmarkIcon(value : Boolean, binding: T, context: Context){
        if (value){
            when(binding){
                is LatestNewsItemBinding ->binding.bookmark.setImageDrawable(context.getDrawable(R.drawable.ic_fill_bookmark))
                is TopNewsItemBinding ->binding.bookmark.setImageDrawable(context.getDrawable(R.drawable.ic_fill_bookmark))
            }

        }else{
            when(binding){
                is LatestNewsItemBinding ->binding.bookmark.setImageDrawable(context.getDrawable(R.drawable.ic_e_bookmark_border))
                is TopNewsItemBinding ->binding.bookmark.setImageDrawable(context.getDrawable(R.drawable.ic_e_bookmark_border))
            }
        }
    }

    fun makeToast(context: Context,message:String , length:Int){
        Toast.makeText(context, message , length).show()
    }

}