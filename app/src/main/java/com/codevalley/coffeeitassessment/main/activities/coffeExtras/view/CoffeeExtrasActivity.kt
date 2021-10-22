package com.codevalley.coffeeitassessment.main.activities.coffeExtras.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.coffeeitassessment.databinding.ActivityCoffeExtrasBinding
import com.codevalley.coffeeitassessment.main.activities.overview.view.OverviewActivity
import com.codevalley.coffeeitassessment.main.adapters.CoffeeExtrasAdapter
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.CoffeeMachineModel
import com.codevalley.coffeeitassessment.utils.ParentClass

class CoffeeExtrasActivity : ParentClass<ActivityCoffeExtrasBinding>() {
    private lateinit var coffeeExtrasAdapter: CoffeeExtrasAdapter
    private var userData: CoffeeMachineModel? = null
    override fun setUpViews() {
        initUi()
        initEventDriven()
    }

    private fun initEventDriven() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.tvNext.setOnClickListener {
            val intent = Intent(this, OverviewActivity::class.java)
            intent.putExtra("userData", userData)
            startActivity(intent)
        }
    }

    private fun initUi() {
        initRecycler()
        getDataAndSetInAdapter()
    }

    override fun getViewBinding() = ActivityCoffeExtrasBinding.inflate(layoutInflater)

    private fun initRecycler() {
        coffeeExtrasAdapter = CoffeeExtrasAdapter(this@CoffeeExtrasActivity)
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvCoffeeExtras.layoutManager = linearLayoutManager
        binding.rvCoffeeExtras.adapter = coffeeExtrasAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDataAndSetInAdapter() {
        userData = intent.getParcelableExtra("userData")
        if (userData != null) {
            coffeeExtrasAdapter.addAll(userData!!)
        }
        coffeeExtrasAdapter.notifyDataSetChanged()
    }
}