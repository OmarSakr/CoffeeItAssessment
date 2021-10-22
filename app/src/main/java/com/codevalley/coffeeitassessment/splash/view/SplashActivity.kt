package com.codevalley.coffeeitassessment.splash.view

import android.content.Intent
import com.codevalley.coffeeitassessment.databinding.ActivitySplashBinding
import com.codevalley.coffeeitassessment.main.activities.home.view.HomeActivity
import com.codevalley.coffeeitassessment.utils.ParentClass

class SplashActivity : ParentClass<ActivitySplashBinding>() {
    override fun setUpViews() {
        initEventDriven()
    }

    private fun initEventDriven() {

        binding.ivCoffeeMachine.setOnClickListener {
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)

        }



    }

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)
}