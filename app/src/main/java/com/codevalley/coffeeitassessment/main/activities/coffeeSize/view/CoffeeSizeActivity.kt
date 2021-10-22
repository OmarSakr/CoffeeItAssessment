package com.codevalley.coffeeitassessment.main.activities.coffeeSize.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.coffeeitassessment.databinding.ActivityCoffeeSizeBinding
import com.codevalley.coffeeitassessment.main.adapters.CoffeeSizesAdapter
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.CoffeeMachineModel
import com.codevalley.coffeeitassessment.utils.ParentClass

class CoffeeSizeActivity : ParentClass<ActivityCoffeeSizeBinding>() {
    private lateinit var coffeeSizesAdapter: CoffeeSizesAdapter

    override fun setUpViews() {
        initUi()
        initEventDriven()
    }

    private fun initEventDriven() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun initUi() {
        initRecycler()
        getDataAndSetInAdapter()
    }

    override fun getViewBinding() = ActivityCoffeeSizeBinding.inflate(layoutInflater)

    private fun initRecycler() {
        coffeeSizesAdapter = CoffeeSizesAdapter(this@CoffeeSizeActivity)
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvCoffeeSizes.layoutManager = linearLayoutManager
        binding.rvCoffeeSizes.adapter = coffeeSizesAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDataAndSetInAdapter() {
        val userData = intent.getParcelableExtra<CoffeeMachineModel>("userData")
        if (userData != null) {
            coffeeSizesAdapter.addAll(userData)
        }
        coffeeSizesAdapter.notifyDataSetChanged()
    }

}