package com.codevalley.coffeeitassessment.models.coffeeMachineModel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize

@Parcelize
data class Type(
    @ColumnInfo(name = "_id") val _id: String,
    @Embedded
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "__selected") var selected: Boolean = false
) : Parcelable