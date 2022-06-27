package com.hxl.arithmagame.presentation.activity

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.hxl.arithmagame.R
import com.hxl.arithmagame.databinding.ActivityMainBinding
import com.hxl.arithmagame.presentation.LanguageHelper
import com.hxl.arithmagame.presentation.fragment.menu.MenuFragment
import com.hxl.arithmagame.presentation.fragment.welcome.WelcomeFragment
import com.hxl.data.repository.PreferenceRepositoryImpl
import com.hxl.data.storage.sharedprefs.SharedPreferenceStorage
import dagger.hilt.android.AndroidEntryPoint

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
        when(vm.welcome){
            false -> replaceFragment(MenuFragment())
            else -> replaceFragment(WelcomeFragment())
        }
    }

    fun replaceFragment(
        fragment: Fragment?,
        backStackTag: String? = null,
        container: Int = R.id.main_container
    ) {
        val transaction = supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_right,
            R.anim.exit_to_right
        ).replace(container, fragment!!)
        if (backStackTag != null) {
            transaction.addToBackStack(backStackTag)
        }
        transaction.commit()
    }

    fun showDialog(dialog: AppCompatDialogFragment, tag: String){
        dialog.show(supportFragmentManager, tag)
    }

    fun restartActivity(){
        startActivity(Intent(this, this::class.java))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}