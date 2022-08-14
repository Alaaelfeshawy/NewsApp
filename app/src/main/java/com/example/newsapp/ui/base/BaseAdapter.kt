package com.example.newsapp.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.domain.model.home.Article
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.ui.util.Util

class BaseAdapter<T,Y:ViewBinding>(private val design :Int ,
                                   var itemClickListener :((T)->Unit)?=null,
                                   val viewHolderFactor :(Y)-> BaseViewHolder<Y,T>) : RecyclerView.Adapter<BaseViewHolder<Y, T>>() {
    private lateinit var binding:ViewBinding
    var list: List<T> = ArrayList()

    private var updateUiListener:UpdateUiListener?=null

    fun setDataList(list: List<T>){
        this.list = list
        notifyDataSetChanged()
    }
    fun setDataList(list: ArrayList<T> ){
        this.list = list
        notifyDataSetChanged()
    }

    fun setUpdateUiListener(updateUiListener:UpdateUiListener){
        this.updateUiListener = updateUiListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Y, T> {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),design,parent,false)
        return viewHolderFactor(binding as Y)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Y, T>, position: Int) {
        list[position]?.let { holder.onBind(position, it) }
        holder.itemView.setOnClickListener {
            list[position]?.let { it1 -> itemClickListener?.invoke(it1) }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    interface UpdateUiListener{
        fun <T : ViewDataBinding> updateUI(article: ArticleModel, binding: T)

        fun <T : ViewDataBinding> checkIfExistAndUpdateUI(article: ArticleModel, binding: T)

    }
}