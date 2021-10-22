package com.codevalley.coffeeitassessment.main.activities.overview.view

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.coffeeitassessment.R
import com.codevalley.coffeeitassessment.databinding.ActivityOverviewBinding
import com.codevalley.coffeeitassessment.main.adapters.SelectedCoffeeExtrasAdapter
import com.codevalley.coffeeitassessment.main.adapters.SelectedCoffeeSizeAdapter
import com.codevalley.coffeeitassessment.main.adapters.SelectedCoffeeTypeAdapter
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.CoffeeMachineModel
import com.codevalley.coffeeitassessment.utils.ParentClass

class OverviewActivity : ParentClass<ActivityOverviewBinding>() {
    private lateinit var selectedCoffeeTypeAdapter: SelectedCoffeeTypeAdapter
    private lateinit var selectedCoffeeSizeAdapter: SelectedCoffeeSizeAdapter
    private lateinit var selectedCoffeeExtrasAdapter: SelectedCoffeeExtrasAdapter
    private var userData: CoffeeMachineModel? = null

    override fun setUpViews() {
        initUi()
        initEventDriven()
    }

    override fun getViewBinding() = ActivityOverviewBinding.inflate(layoutInflater)


    private fun initEventDriven() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.tvEditCoffeeType.setOnClickListener {
            if (binding.rvCoffeeTypes.visibility == View.VISIBLE) {
                binding.rvCoffeeTypes.visibility = View.GONE
            } else if (binding.rvCoffeeTypes.visibility == View.GONE) {
                binding.rvCoffeeTypes.visibility = View.VISIBLE
            }
        }
        binding.tvEditCoffeeSize.setOnClickListener {
            if (binding.rvCoffeeSizes.visibility == View.VISIBLE) {
                binding.rvCoffeeSizes.visibility = View.GONE
            } else if (binding.rvCoffeeSizes.visibility == View.GONE) {
                binding.rvCoffeeSizes.visibility = View.VISIBLE
            }
        }
        binding.tvBrewYourCoffee.setOnClickListener {
            makeToast(this,getString(R.string.enjoyYourCoffee))
        }
    }

    private fun initUi() {
        initCoffeeTypeRecycler()
        initCoffeeSizeRecycler()
        initCoffeeExtrasRecycler()
        getData()
    }


    private fun initCoffeeTypeRecycler() {
        selectedCoffeeTypeAdapter = SelectedCoffeeTypeAdapter(this@OverviewActivity)
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvCoffeeTypes.layoutManager = linearLayoutManager
        binding.rvCoffeeTypes.adapter = selectedCoffeeTypeAdapter
    }

    private fun initCoffeeSizeRecycler() {
        selectedCoffeeSizeAdapter = SelectedCoffeeSizeAdapter(this@OverviewActivity)
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvCoffeeSizes.layoutManager = linearLayoutManager
        binding.rvCoffeeSizes.adapter = selectedCoffeeSizeAdapter
    }

    private fun initCoffeeExtrasRecycler() {
        selectedCoffeeExtrasAdapter = SelectedCoffeeExtrasAdapter(this@OverviewActivity)
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvCoffeeExtras.layoutManager = linearLayoutManager
        binding.rvCoffeeExtras.adapter = selectedCoffeeExtrasAdapter
    }


    private fun getData() {
        userData = intent.getParcelableExtra("userData")
        setDataInCoffeeType()
        binding.tvCoffeeType.text = userData!!.selectedCoffeeType
        setDataInCoffeeSize()
        binding.tvCoffeeSize.text = userData!!.selectedCoffeeSize
        setDataInCoffeeExtra()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setDataInCoffeeType() {
        if (userData != null) {
            selectedCoffeeTypeAdapter.addAll(userData!!.types)
        }
        selectedCoffeeTypeAdapter.notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setDataInCoffeeSize() {
        if (userData != null) {
            selectedCoffeeSizeAdapter.addAll(userData!!.sizes)
        }
        selectedCoffeeSizeAdapter.notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setDataInCoffeeExtra() {
        if (userData != null) {
            selectedCoffeeExtrasAdapter.addAll(userData!!.extras)
        }
        selectedCoffeeExtrasAdapter.notifyDataSetChanged()

    }

}