package com.codevalley.coffeeitassessment.network

class ApiHelper(private val apiService: AppServices) {

    suspend fun getCoffeeMachine() = apiService.getCoffeeMachine()

}