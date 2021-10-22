package com.codevalley.coffeeitassessment.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.CoffeeMachineModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDao {
    @Query("SELECT * FROM coffeeData")
    fun getCoffee(): Flow<List<CoffeeMachineModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCoffee(coffeeMachineModel: CoffeeMachineModel)

    @Query("DELETE FROM coffeeData")
    suspend fun deleteAll()

}