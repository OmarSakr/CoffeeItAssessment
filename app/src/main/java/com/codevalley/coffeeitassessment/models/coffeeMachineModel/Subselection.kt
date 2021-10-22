package com.codevalley.coffeeitassessment.models.coffeeMachineModel

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize


@Parcelize
data class Subselection(
    @ColumnInfo(name = "_id")  val _id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "selected") var selected: Boolean = false,
    @ColumnInfo(name = "selectedPosition") var selectedPosition: Int
) : Parcelable