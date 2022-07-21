package com.hxl.arithmathics.presentation.activity

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.hxl.arithmathics.R
import com.hxl.arithmathics.databinding.ActivityMainBinding
import com.hxl.arithmathics.presentation.LanguageHelper
import com.hxl.arithmathics.presentation.fragment.menu.MenuFragment
import com.hxl.arithmathics.presentation.fragment.welcome.WelcomeFragment
import com.hxl.data.repository.PreferenceRepositoryImpl
import com.hxl.data.storage.sharedprefs.SharedPreferenceStorage
import com.hxl.data.model.DifficultyEnums
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val vm: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun attachBaseContext(newBase: Context) {
        val sharedPref = PreferenceRepositoryImpl(SharedPreferenceStorage(newBase))
        AppCompatDelegate.setDefaultNightMode(sharedPref.theme)
        val localeUpdatedContext: ContextWrapper =
            LanguageHelper(newBase).updateLocale(sharedPref.language)
        super.attachBaseContext(localeUpdatedContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply { setKeepOnScreenCondition { false } }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        try { Log.e("DATA_TEST", vm.custom.levels.toString()) }
        catch (e: Exception) { vm.custom = DifficultyEnums.CUSTOM.questionDifficulty }
        try { Log.e("DATA_TEST", vm.gameHistory.size.toString()) }
        catch (e: Exception) { vm.gameHistory = Stack() }

        when (vm.welcome) {
            false -> replaceFragment(MenuFragment())
            else -> replaceFragment(WelcomeFragment())
        }
    }

    fun replaceFragment(fragment: Fragment, backStack: Boolean = false) {
        val backStateName = fragment.javaClass.name
        val manager: FragmentManager = supportFragmentManager
        val fragmentPopped: Boolean = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) { //fragment not in back stack, create it.
            val ft: FragmentTransaction = manager.beginTransaction()
            ft.replace(R.id.main_container, fragment)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            if (!backStack) {
                manager.popBackStack()
            }
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

    fun showDialog(dialog: AppCompatDialogFragment, tag: String) {
        dialog.show(supportFragmentManager, tag)
    }

    fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    fun restartActivity() {
        startActivity(Intent(this, this::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}