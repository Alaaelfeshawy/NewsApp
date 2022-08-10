package com.example.newsapp.ui.news_details

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

import com.bumptech.glide.Glide
import com.example.domain.util.AppConstants
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsDetailsBinding
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.model.home.ArticleModelMapper
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.util.Util

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment<FragmentNewsDetailsBinding>() {
    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!
    private var articleModel : ArticleModel ?= null
    private val viewModel: NewsDetailsViewModel by lazy {
        ViewModelProvider(this)[NewsDetailsViewModel::class.java]
    }
    override val layoutId: Int
        get() = R.layout.fragment_news_details

    override fun viewSetup() {
        _binding = viewDataBinding
        arguments?.let {
            articleModel = it.getParcelable(AppConstants.ARTICLE_MODEL)
        }
        Glide.with(requireContext())
            .load(articleModel?.urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.articleImage)
        binding.articleTitle.text = articleModel?.title
        binding.articleDesc.text = articleModel?.description
        binding.articleAuthor.text = articleModel?.author
        binding.articleDate.text= articleModel?.publishedAt?.let { Util.covertDate(it) }
        binding.openWebView.setOnClickListener {
            navController?.navigate(NewsDetailsFragmentDirections.actionNewsDetailsFragmentToWebViewFragment(articleModel?.url ?: ""))
        }
        val article = ArticleModelMapper.mapper.toDomain(articleModel)
        binding.bookmarkIcon.setOnClickListener {
            article?.let {
                viewModel.isArticleExistInDbAnUpdate(it) {
                    if (it) {
                        binding.bookmarkIcon.setImageDrawable(requireContext().getDrawable(R.drawable.ic_fill_bookmark))
                    } else {
                        binding.bookmarkIcon.setImageDrawable(requireContext().getDrawable(R.drawable.ic_e_bookmark_border))
                    }
                }
            }
        }
    }

    override fun viewModelSetup() {
        viewModel.stateListener.success.observe(viewLifecycleOwner){
            it?.let {
                makeToast(it, Toast.LENGTH_LONG)
            }
        }
        val article = ArticleModelMapper.mapper.toDomain(articleModel)
        article?.let { it1 ->
            viewModel.isArticleExistInDb(it1) {
                if (it) {
                    binding.bookmarkIcon.setImageDrawable(requireContext().getDrawable(R.drawable.ic_fill_bookmark))
                } else {
                    binding.bookmarkIcon.setImageDrawable(requireContext().getDrawable(R.drawable.ic_e_bookmark_border))
                }
            }
        }
    }

}