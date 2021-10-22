package com.codevalley.coffeeitassessment.utils

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.codevalley.coffeeitassessment.dao.CoffeeDao
import com.codevalley.coffeeitassessment.models.coffeeMachineModel.*
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CoffeeRoomDatabaseTest {
    private lateinit var db: CoffeeRoomDatabase
    private lateinit var dao: CoffeeDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CoffeeRoomDatabase::class.java).build()
        dao = db.coffeeDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun addAndReadCoffee() = runBlocking {
        val type = Type("1", "Espresso", false)
        val size = Size(1, "1", "Large", false)
        val subSelection = Subselection("1", "No sugar", false, 0)
        val extra = Extra("1", "Sugar", listOf(subSelection), false, 0)
        val coffeeMachineModel = CoffeeMachineModel(
            1, "1", listOf(extra),
            listOf(size), listOf(type), "Espresso", "Large"
        )
        dao.addCoffee(coffeeMachineModel)
        val coffee = dao.getCoffee()
        TestCase.assertNotNull(coffee)
    }

}