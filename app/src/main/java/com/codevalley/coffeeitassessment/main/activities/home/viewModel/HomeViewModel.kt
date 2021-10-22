package com.codevalley.coffeeitassessment.main.activities.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.CoffeeMachineModel
import com.codevalley.coffeeitassessment.utils.MainRepository
import com.codevalley.coffeeitassessment.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val _mutableStateFlowHome: MutableStateFlow<Resource<Response<CoffeeMachineModel>>> =
        MutableStateFlow(
            Resource.loading(null)
        )
    val mutableStateFlowHomeHome: MutableStateFlow<Resource<Response<CoffeeMachineModel>>> =
        _mutableStateFlowHome

    fun getCoffeeMachine() {
        viewModelScope.launch(Dispatchers.IO)
        {
            _mutableStateFlowHome.emit(Resource.loading(data = null))
            try {
                _mutableStateFlowHome.emit(
                    Resource.success(
                        data = mainRepository.getCoffeeMachine()
                    )
                )
            } catch (exception: Exception) {
                _mutableStateFlowHome.emit(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Something went wrong.!"
                    )
                )
            }
        }
    }

    val getCoffee: Flow<List<CoffeeMachineModel>> = mainRepository.getCoffee


    fun addCoffee(coffeeMachineModel: CoffeeMachineModel) = viewModelScope.launch {
        mainRepository.addCoffee(coffeeMachineModel)
    }

    fun deleteAll() = viewModelScope.launch {
        mainRepository.deleteAll()
    }

}