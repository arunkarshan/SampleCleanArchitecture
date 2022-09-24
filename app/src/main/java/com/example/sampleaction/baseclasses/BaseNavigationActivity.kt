package com.example.sampleaction.baseclasses

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.sampleaction.R

open class BaseNavigationActivity : AppCompatActivity() {
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
    }

    private val navController by lazy {
        navHostFragment.navController
    }

    open var navGraphId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        setNavGraph()
    }

    open fun setNavGraph(){
        navGraphId.takeIf { it != -1 }?.let { navController.setGraph(it) }
    }
}