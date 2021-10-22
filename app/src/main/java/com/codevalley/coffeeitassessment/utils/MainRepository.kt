package com.codevalley.coffeeitassessment.utils

import androidx.annotation.WorkerThread
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.CoffeeMachineModel
import com.codevalley.coffeeitassessment.network.ApiHelper
import com.codevalley.coffeeitassessment.dao.CoffeeDao
import kotlinx.coroutines.flow.Flow

class MainRepository(private val apiHelper: ApiHelper,private val coffeeDao: CoffeeDao) {
    suspend fun getCoffeeMachine() = apiHelper.getCoffeeMachine()

    val getCoffee: Flow<List<CoffeeMachineModel>> = coffeeDao.getCoffee()

    @WorkerThread
    suspend fun addCoffee(coffeeMachineModel: CoffeeMachineModel) {
        coffeeDao.addCoffee(coffeeMachineModel)
    }


    @WorkerThread
    suspend fun deleteAll() {
        coffeeDao.deleteAll()
    }




}
