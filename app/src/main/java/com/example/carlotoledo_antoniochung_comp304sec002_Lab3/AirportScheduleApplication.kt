package com.example.carlotoledo_antoniochung_comp304sec002_Lab3

import android.app.Application
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.AppDatabase

class AirportScheduleApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}