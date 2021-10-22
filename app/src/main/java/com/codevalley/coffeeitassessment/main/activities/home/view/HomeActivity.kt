package com.codevalley.coffeeitassessment.main.activities.home.view

import android.annotation.SuppressLint
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.coffeeitassessment.databinding.ActivityHomeBinding
import com.codevalley.coffeeitassessment.main.activities.home.viewModel.HomeViewModel
import com.codevalley.coffeeitassessment.main.activities.home.viewModel.HomeViewModelFactory
import com.codevalley.coffeeitassessment.main.adapters.HomeAdapter
import com.codevalley.coffeeitassessment.network.ApiHelper
import com.codevalley.coffeeitassessment.utils.AppController
import com.codevalley.coffeeitassessment.utils.ParentClass
import com.codevalley.coffeeitassessment.utils.RetrofitClient
import com.codevalley.coffeeitassessment.utils.Status
import kotlinx.coroutines.flow.collect

class HomeActivity : ParentClass<ActivityHomeBinding>() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter


    override fun setUpViews() {
        initUi()
        initEventDriven()
    }

    private fun initEventDriven() {

    }

    private fun initUi() {
        homeViewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(
                ApiHelper(RetrofitClient.apiInterface),
                (application as AppController).database.coffeeDao()
            )
        ).get(HomeViewModel::class.java)

        if (checkForInternet(this@HomeActivity)) {
            homeViewModel.deleteAll()
            homeViewModel.getCoffeeMachine()
            getCoffeeMachine()

        } else {
            getCoffeeFromCache()

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getCoffeeFromCache() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.getCoffee.collect {
                initRecycler()
                homeAdapter.addAll(it[0])
                homeAdapter.notifyDataSetChanged()

            }
        }
    }


    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    private fun initRecycler() {

        homeAdapter = HomeAdapter(this@HomeActivity)

        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.rvCoffeeTypes.layoutManager = linearLayoutManager

        binding.rvCoffeeTypes.adapter = homeAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getCoffeeMachine() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.mutableStateFlowHomeHome.collect {
                if (it.status == Status.SUCCESS) {
                    initRecycler()
                    binding.progressBar.visibility = GONE
                    homeAdapter.addAll(it.data!!.body()!!)
                    homeAdapter.notifyDataSetChanged()

                    homeViewModel.addCoffee(it.data.body()!!)

                }
                if (it.status == Status.ERROR) {
                    binding.progressBar.visibility = GONE

                    makeToast(this@HomeActivity, it.message.toString())

                }
                if (it.status == Status.LOADING) {
                    binding.progressBar.visibility = VISIBLE

                }
            }
        }
    }


}