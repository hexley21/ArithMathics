package com.hxl.arithmagame.presentation.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.hxl.arithmagame.R
import com.hxl.arithmagame.databinding.ActivityMainBinding
import com.hxl.arithmagame.presentation.fragment.MenuFragment
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
        super.attachBaseContext(newBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply { setKeepOnScreenCondition { false } }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val menuFragment = MenuFragment()
        replaceFragment(menuFragment)
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
}