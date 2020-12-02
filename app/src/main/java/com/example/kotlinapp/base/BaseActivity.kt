package com.example.kotlinapp.base

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapp.R
import com.example.kotlinapp.iu.changeLanguage
import com.example.kotlinapp.iu.loadLocale
import com.example.kotlinapp.iu.showToast

abstract class BaseActivity <ViewModel:BaseViewModel>(private var layoutId:Int): AppCompatActivity() {
    abstract val viewModel: ViewModel
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        initLanguage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setupViews()
        setupLiveData()
        setupFetchRequests()
        showError()
    }

    override fun onResume() {
        loadLocale(this)
        super.onResume()
    }

    private fun initLanguage() {
    }

    abstract fun setupViews()
    abstract fun setupLiveData()
    abstract fun setupFetchRequests()

    private fun showError() {
        viewModel.errorMessage.observeForever {
            showToast(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.edit, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action -> {
                changeLanguage()
            }
        }
        return true
    }
}