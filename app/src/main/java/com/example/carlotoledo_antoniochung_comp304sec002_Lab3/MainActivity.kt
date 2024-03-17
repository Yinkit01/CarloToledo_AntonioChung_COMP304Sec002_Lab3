package com.example.carlotoledo_antoniochung_comp304sec002_Lab3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.AppDatabase
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule.Schedule
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        // Populate the database with initial data
        populateDatabase()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun populateDatabase() {
        val database = AppDatabase.getDatabase(this)
        val scheduleDao = database.scheduleDao()

        // Use a coroutine to insert data on a background thread
        lifecycleScope.launch(Dispatchers.IO) {
            // Check if the database is empty before inserting
            if (scheduleDao.getAll().first().isEmpty()) {
                val schedules = listOf(
                    Schedule(0, "Air Canada", "10:00 AM", "T1", "OnTime", "StatusHere"), // Add the status value
                    Schedule(1, "Air France", "11:30 AM", "T2", "Delayed", "StatusHere"), // Add the status value
                    // Add more sample schedules as needed
                )
                schedules.forEach { scheduleDao.insert(it) }
            }
        }
    }

}
