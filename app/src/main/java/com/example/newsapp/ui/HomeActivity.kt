package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.base.BaseActivity
import com.example.newsapp.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityMainBinding>(){
    private var binding: ActivityMainBinding? = null
    private var navController : NavController ? = null

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun activityCreated() {
        binding = viewDataBinding
        setUpNavigation()
        if (viewModel.getAppMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_NewsApp_dark)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            setTheme(R.style.AppTheme)
        }
    }

    private fun setUpNavigation(){
        setSupportActionBar(binding?.toolbar)
        setTitle(R.string.app_name)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navController = navHostFragment?.navController
        navController?.let { binding?.navView?.setupWithNavController(it) }
        binding?.navView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    navController?.navigate(R.id.homeFragment)
                }
                R.id.nav_search -> {
                    navController?.navigate(R.id.searchFragment)
                }
                R.id.nav_book_mark -> {
                    navController?.navigate(R.id.bookmarkFragment)
                }
                R.id.nav_settings -> {
                    navController?.navigate(R.id.settingsFragment)
                }
            }
            true
        }

    }


}