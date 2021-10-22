package com.codevalley.coffeeitassessment.network

import com.codevalley.coffeeitassessment.models.coffeeMachineModel.CoffeeMachineModel
import retrofit2.Response
import retrofit2.http.GET


interface AppServices {
    @GET(AppUrls.coffeeMachine)
    suspend fun getCoffeeMachine(): Response<CoffeeMachineModel>

}