package com.example.newsapp.ui.home.view_holder

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.TopNewsItemBinding
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.ui.base.BaseViewHolder
import com.example.newsapp.ui.util.Util


class TopNewsViewHolder(private val itemBinding: ViewBinding,
                        private val context: Context,
                        private var bookmarkClickListener :((ArticleModel ,TopNewsItemBinding )->Unit)?=null,
                        private var updateUi :((ArticleModel,TopNewsItemBinding )->Unit)?=null
) : BaseViewHolder<TopNewsItemBinding, ArticleModel>(
    itemBinding
) {
    override fun onBind(position: Int, model: ArticleModel) {
        updateUi?.invoke(model,binding)
        Glide.with(itemBinding.root)
            .load(model.urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.image)
        binding.title.text = model.title
        binding.date.text= model.publishedAt?.let { Util.covertDate(it) }
        binding.bookmark.setOnClickListener {
            bookmarkClickListener?.invoke(model , binding)
        }
//        if (model.id != null){
//            binding.bookmark.setImageDrawable(context.getDrawable(R.drawable.ic_fill_bookmark))
//        }else{
//            binding.bookmark.setImageDrawable(context.getDrawable(R.drawable.ic_e_bookmark_border))
//        }
    }

    override fun onDetached() {
    }

    override fun onViewRecycled() {
    }
}