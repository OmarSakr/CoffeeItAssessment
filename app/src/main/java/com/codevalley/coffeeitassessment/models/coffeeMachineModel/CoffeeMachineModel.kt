package com.codevalley.coffeeitassessment.models.coffeeMachineModel

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "coffeeData")
data class CoffeeMachineModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val _id: String,
    val extras: List<Extra>,
    val sizes: List<Size>,
    val types: List<Type>,
    var selectedCoffeeType: String?,
    var selectedCoffeeSize: String?

) : Parcelable