package com.dkgtech.notesappdarkmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.dkgtech.notesappdarkmode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {

            val toolbar = Toolbar(this@MainActivity)
            setSupportActionBar(toolbar)

            navController = findNavController(R.id.fragmentContainerView)
            setupActionBarWithNavController(navController)

        }

        fun onNavigateUp(): Boolean {
            return navController.navigateUp() || super.onNavigateUp()
        }
    }
}