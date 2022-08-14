package com.example.newsapp.ui.bookmark

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentBookmarkBinding
import com.example.newsapp.databinding.LatestNewsItemBinding
import com.example.newsapp.databinding.TopNewsItemBinding
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.model.home.ArticleModelMapper
import com.example.newsapp.ui.base.BaseAdapter
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.home.view_holder.LatestNewsViewHolder
import com.example.newsapp.ui.home.view_holder.TopNewsViewHolder
import com.example.newsapp.ui.util.Util
import com.example.newsapp.ui.util.Util.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment :  BaseFragment<FragmentBookmarkBinding>() {
    private val viewModel: BookmarkViewModel by lazy {
        ViewModelProvider(this)[BookmarkViewModel::class.java]
    }
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private val adapter: BaseAdapter<ArticleModel, LatestNewsItemBinding> by lazy {
        BaseAdapter(R.layout.latest_news_item,{
            findNavController().navigate(BookmarkFragmentDirections.actionBookmarkFragmentToNewsDetailsFragment(it))
        }){
            LatestNewsViewHolder(it , {
                    model,_->
                ArticleModelMapper.mapper.toDomain(model)?.let {
                    viewModel.deleteArticleFromDb(it)
                }
            }){ model , binding->
                ArticleModelMapper.mapper.toDomain(model)?.let {
                    Util.updateUI(it,binding,viewModel,requireContext())
                }
            }
        }
    }
    override val layoutId: Int
        get() = R.layout.fragment_bookmark

    override fun viewSetup() {
        _binding = viewDataBinding
        binding.bookmarkRecyclerView.adapter = adapter
        if (viewModel.getAppMode()){
            binding.favoriteImage.setImageDrawable(requireContext().getDrawable(R.drawable.heart_white_ic))
        }else{
            binding.favoriteImage.setImageDrawable(requireContext().getDrawable(R.drawable.favourite))
        }
    }

    override fun viewModelSetup() {
        viewModel.articles.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()){
                binding.bookmarkRecyclerView.visibility=View.VISIBLE
                binding.emptyList.visibility=View.GONE
                adapter.setDataList(it)
            }else{
                binding.bookmarkRecyclerView.visibility=View.GONE
                binding.emptyList.visibility=View.VISIBLE
            }
        }
        viewModel.stateListener.success.observe(viewLifecycleOwner){
            it?.let {
               makeToast(requireContext(),it,Toast.LENGTH_LONG)
            }
        }
    }


}