package com.codevalley.coffeeitassessment.main.activities.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codevalley.coffeeitassessment.network.ApiHelper
import com.codevalley.coffeeitassessment.utils.MainRepository
import com.codevalley.coffeeitassessment.dao.CoffeeDao

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val apiHelper: ApiHelper,private val coffeeDao: CoffeeDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(MainRepository(apiHelper,coffeeDao)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}