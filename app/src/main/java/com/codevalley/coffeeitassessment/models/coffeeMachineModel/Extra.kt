package com.codevalley.coffeeitassessment.models.coffeeMachineModel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize

@Parcelize
data class Extra(
    @ColumnInfo(name = "_id") val _id: String,
    @ColumnInfo(name = "name") val name: String,
    val subselections: List<Subselection>,
    @ColumnInfo(name = "selected") var selected: Boolean = false,
    @ColumnInfo(name = "selectedExtrasPosition") var selectedExtrasPosition: Int
) : Parcelable