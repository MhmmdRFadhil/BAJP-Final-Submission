package com.ryz.moviecatalogue.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = binding.bottomNavMain
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        NavigationUI.setupWithNavController(
            bottomNavigation,
            navHostFragment.navController
        )
    }
}