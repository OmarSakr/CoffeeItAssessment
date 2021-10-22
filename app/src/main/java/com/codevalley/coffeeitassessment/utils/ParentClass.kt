package com.codevalley.coffeeitassessment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

import android.content.Intent
import com.codevalley.coffeeitassessment.R


abstract class ParentClass<B : ViewBinding> : AppCompatActivity() {

    lateinit var binding: B

    private val disposableContainer = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpViews()
    }

    abstract fun getViewBinding(): B
    open fun setUpViews() {

    }


    /**
     * loadImages
     */
    fun loadImageWithPicasso(url: String?, context: Context?, imageView: ImageView?) {
        if (url != "") {
            Picasso.with(context).load(url).error(R.drawable.default_image).into(imageView)
        } else {
            Picasso.with(context).load(R.drawable.default_image).error(R.drawable.default_image)
                .into(imageView)
        }
    }

    /**
     * check network availability
     */
    fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection

        // Returns a Network object corresponding to
        // the currently active default data network.
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    }


    fun makeToast(context: Context, msg: Int) {
        Toast.makeText(context, context.resources.getString(msg), Toast.LENGTH_SHORT).show()
    }

    fun makeToast(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    fun dismissKeyboard() {
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    fun Disposable.addToContainer() = disposableContainer.add(this)

    override fun onDestroy() {
        disposableContainer.clear()
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        onLeaveThisActivity()
    }

    protected open fun onLeaveThisActivity() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        onStartNewActivity()
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        super.startActivity(intent, options)
        onStartNewActivity()
    }


    protected open fun onStartNewActivity() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }


}