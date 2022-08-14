package com.example.newsapp.ui.home.view_holder

import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.LatestNewsItemBinding
import com.example.newsapp.databinding.TopNewsItemBinding
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.ui.base.BaseAdapter
import com.example.newsapp.ui.base.BaseViewHolder
import com.example.newsapp.ui.util.Util


class LatestNewsViewHolder(private val itemBinding: ViewBinding,
                           private var updateUiListener: BaseAdapter.UpdateUiListener
) : BaseViewHolder<LatestNewsItemBinding, ArticleModel>(
    itemBinding
) {
    override fun onBind(position: Int, model: ArticleModel) {
        updateUiListener.updateUI(model,binding)
        Glide.with(itemBinding.root)
            .load(model.urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.image)
        binding.title.text = model.title
        binding.date.text= model.publishedAt?.let { Util.covertDate(it) }
        binding.bookmark.setOnClickListener {
            updateUiListener.checkIfExistAndUpdateUI(model,binding)
        }

    }

    override fun onDetached() {
    }

    override fun onViewRecycled() {
    }
}