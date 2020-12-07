package com.example.kotlinapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.util.*


fun ImageView.loadImage(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

private fun setLocale(s: String, context: Context) {
    val locale = Locale(s)
    Locale.setDefault(locale)
    val config = Configuration()
    config.locale = locale
    context.resources.updateConfiguration(
        config,
        context.resources.displayMetrics
    )

    App.preferences.saveLanguage(s)
}

fun loadLocale(context: Context) {
    var language: String? = App.preferences.preference
    if (language != null) {
        setLocale(language, context)
    }
}

fun Activity.changeLanguage() {
    val listItems = arrayOf("Русский", "English", "Корейский", "Кыргызский")
    val mBuilder = AlertDialog.Builder(this)

    mBuilder.setTitle(getString(R.string.выберите_язык))
    mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
        when (which) {
            0 -> {
                setLocale("ru", this)
            }
            1 -> {
                setLocale("chr-rUS", this)
            }
            2 -> {
                setLocale("ko-rKR", this)
            }
            3 -> {
                setLocale("ky", this)
            }

        }
        this.recreate()
        dialog.dismiss()
    }
    val mDialog = mBuilder.create()
    mDialog.show()
}

fun View.showSnackBar(context: Context, message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}


