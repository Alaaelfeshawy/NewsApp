package com.example.newsapp.ui.home

import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.TopNewsItemBinding
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.ui.base.BaseViewHolder
import com.example.newsapp.ui.util.Util


class TopNewsViewHolder(private val itemBinding: ViewBinding) : BaseViewHolder<TopNewsItemBinding, ArticleModel>(
    itemBinding
) {
    override fun onBind(position: Int, model: ArticleModel) {
        Glide.with(itemBinding.root)
            .load(model.urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.image)
        binding.title.text = model.title
        binding.date.text= model.publishedAt?.let { Util.covertDate(it) }
    }

    override fun onDetached() {
    }

    override fun onViewRecycled() {
    }
}