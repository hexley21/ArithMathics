package com.hxl.arithmagame.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.hxl.arithmagame.R
import com.hxl.arithmagame.databinding.ActivityMainBinding
import com.hxl.arithmagame.presentation.fragment.MenuFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
}