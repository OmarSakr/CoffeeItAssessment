package com.codevalley.coffeeitassessment.models.coffeeMachineModel

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Size(
    @ColumnInfo(name = "__v")  val __v: Int,
    @ColumnInfo(name = "_id") val _id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "_selected") var selected: Boolean = false
) : Parcelable
