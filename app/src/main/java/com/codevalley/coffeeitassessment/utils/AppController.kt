package com.codevalley.coffeeitassessment.utils

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AppController : MultiDexApplication() {

    private val applicationScope = CoroutineScope(SupervisorJob())


    val database by lazy { CoffeeRoomDatabase.getDatabase(this, applicationScope) }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


}