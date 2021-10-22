package com.codevalley.coffeeitassessment.utils

import androidx.room.TypeConverter
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.Extra
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.Size
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.Subselection
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.Type
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun extraListToJson(value: List<Extra>?) = Gson().toJson(value)

    @TypeConverter
    fun extraJsonToList(value: String) = Gson().fromJson(value, Array<Extra>::class.java).toList()

    @TypeConverter
    fun typeListToJson(value: List<Type>?) = Gson().toJson(value)

    @TypeConverter
    fun typeJsonToList(value: String) = Gson().fromJson(value, Array<Type>::class.java).toList()

    @TypeConverter
    fun sizeListToJson(value: List<Size>?) = Gson().toJson(value)

    @TypeConverter
    fun sizeJsonToList(value: String) = Gson().fromJson(value, Array<Size>::class.java).toList()

    @TypeConverter
    fun subselectionListToJson(value: List<Subselection>?) = Gson().toJson(value)

    @TypeConverter
    fun subselectionJsonToList(value: String) =
        Gson().fromJson(value, Array<Subselection>::class.java).toList()
}