package com.example.newsapp.ui.home

import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentHomeBinding
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
class HomeFragment : BaseFragment<FragmentHomeBinding>() , SwipeRefreshLayout.OnRefreshListener , BaseAdapter.UpdateUiListener  {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }
    private val adapter: BaseAdapter<ArticleModel, LatestNewsItemBinding> by lazy {
        BaseAdapter(R.layout.latest_news_item, {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(
                it))
        }) {
            LatestNewsViewHolder(it, this)
        }
    }

    private val topNewsAdapter: BaseAdapter<ArticleModel, TopNewsItemBinding> by lazy {
        BaseAdapter(R.layout.top_news_item, {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(
                it))
        }) {
            TopNewsViewHolder(it, this)
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun viewSetup() {
        _binding = viewDataBinding
        viewModel.getLatestNews()
        viewModel.getTopNews()
        adapter.setUpdateUiListener(this)
        binding.articlesRecyclerView.adapter = adapter
        binding.topNewRecyclerView.adapter = topNewsAdapter
        binding.tryAgain.setOnClickListener {
            viewModel.getLatestNews()
            viewModel.getTopNews()
        }
        binding.mainSwipeRefreshLayout.setOnRefreshListener(this)
        binding.mainSwipeRefreshLayout.setColorSchemeResources(
            R.color.black,
            R.color.black,
            R.color.black,
            R.color.black
        )
    }

    override fun viewModelSetup() {
        viewModel.homeData.observe(viewLifecycleOwner){
            it?.let {
                binding.mainView.visibility=View.VISIBLE
                adapter.setDataList(it)
            }
        }
        viewModel.topNews.observe(viewLifecycleOwner){
            it?.let {
                binding.mainView.visibility=View.VISIBLE
                topNewsAdapter.setDataList(it)
            }
        }
        viewModel.stateListener.loading.observe(viewLifecycleOwner){
            it?.let {
               if(it){
                   binding.noInternetLayout.visibility=View.GONE
                   binding.mainView.visibility=View.GONE
                   binding.progressBar.visibility=View.VISIBLE
//                   Util.showLoading(requireContext())
               }else{
                   binding.progressBar.visibility=View.GONE
//                   Util.dismissLoading()
               }
            }
        }
        viewModel.stateListener.errorMessage.observe(viewLifecycleOwner){
            message -> message?.let {
            makeToast(requireContext(),it,Toast.LENGTH_SHORT)
            }
        }
        viewModel.stateListener.success.observe(viewLifecycleOwner){
                message -> message?.let {
            makeToast(requireContext(),it,Toast.LENGTH_SHORT)
        }
        }
        viewModel.noInternet.observe(viewLifecycleOwner){
            it?.let {
                if(it){
                    binding.noInternetLayout.visibility=View.VISIBLE
                    binding.mainView.visibility=View.GONE
                }else{
                    binding.noInternetLayout.visibility=View.GONE
                    binding.mainView.visibility=View.VISIBLE
                }
            }
        }
    }

    override fun onRefresh() {
        binding.mainSwipeRefreshLayout.isRefreshing = true
        viewModel.getLatestNews()
        viewModel.getTopNews()
        binding.mainSwipeRefreshLayout.isRefreshing = false
    }

    override fun <T : ViewDataBinding> updateUI(model: ArticleModel, binding: T) {
        val article = ArticleModelMapper.mapper.toDomain(model)
        article?.let {
            viewModel.isArticleExistInDb(it){
                Util.updateBookmarkIcon(it, binding, requireContext())
            }
        }
    }

    override fun <T : ViewDataBinding> checkIfExistAndUpdateUI(
        model: ArticleModel, binding: T) {
        val article = ArticleModelMapper.mapper.toDomain(model)
        article?.let {
            viewModel.isArticleExistInDbAnUpdate(it){
                Util.updateBookmarkIcon(it,
                    binding,
                    requireContext())
            }
        }
    }
}