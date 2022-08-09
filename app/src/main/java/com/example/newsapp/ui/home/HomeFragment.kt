package com.example.newsapp.ui.home

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.databinding.LatestNewsItemBinding
import com.example.newsapp.databinding.TopNewsItemBinding
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.ui.base.BaseAdapter
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() , SwipeRefreshLayout.OnRefreshListener  {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }
    private val adapter: BaseAdapter<ArticleModel, LatestNewsItemBinding> by lazy {
        BaseAdapter(R.layout.latest_news_item,{

        }){
            LatestNewsViewHolder(it)
        }
    }
    private val topNewsAdapter: BaseAdapter<ArticleModel, TopNewsItemBinding> by lazy {
        BaseAdapter(R.layout.top_news_item,{

        }){
            TopNewsViewHolder(it)
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun viewSetup() {
        _binding = viewDataBinding
        viewModel.getLatestNews()
        viewModel.getTopNews()
        binding.articlesRecyclerView.adapter = adapter
        binding.topNewRecyclerView.adapter = topNewsAdapter
        binding.tryAgain.setOnClickListener {viewModel.getLatestNews()}
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
                binding.articlesRecyclerView.visibility=View.VISIBLE
                adapter.setDataList(it)
            }
        }
        viewModel.topNews.observe(viewLifecycleOwner){
            it?.let {
                binding.topNewRecyclerView.visibility=View.VISIBLE
                topNewsAdapter.setDataList(it)
            }
        }
        viewModel.stateListener.loading.observe(viewLifecycleOwner){
            it?.let {
               if(it){
                   binding.noInternetLayout.visibility=View.GONE
                   binding.articlesRecyclerView.visibility=View.GONE
                   Util.showLoading(requireContext())
               }else{
                   Util.dismissLoading()
               }
            }
        }
        viewModel.stateListener.errorMessage.observe(viewLifecycleOwner){
            message -> message?.let {
            makeToast(it,Toast.LENGTH_SHORT)
            }
        }
        viewModel.error.observe(viewLifecycleOwner){
                message -> message?.let {
            makeToast(it,Toast.LENGTH_SHORT)
        }
        }
        viewModel.noInternet.observe(viewLifecycleOwner){
            it?.let {
                if(it){
                    binding.noInternetLayout.visibility=View.VISIBLE
                    binding.articlesRecyclerView.visibility=View.GONE
                }else{
                    binding.noInternetLayout.visibility=View.GONE
                    binding.articlesRecyclerView.visibility=View.VISIBLE
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
}