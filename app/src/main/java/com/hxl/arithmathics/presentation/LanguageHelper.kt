package com.hxl.arithmathics.presentation

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.*

/**
 * Helper class that updates Language according to preference & wraps in context.
 */
class LanguageHelper(private val base: Context) : ContextWrapper(base) {

    fun updateLocale(language: String): ContextWrapper {
        var context = base
        val locale = Locale(language)
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(locale)
            LocaleList.setDefault(localeList)
            configuration.setLocales(localeList)
        } else {
            configuration.locale = locale
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            context = context.createConfigurationContext(configuration)
        } else {
            resources.updateConfiguration(configuration , resources.displayMetrics)
        }
        return LanguageHelper(context)
    }

}