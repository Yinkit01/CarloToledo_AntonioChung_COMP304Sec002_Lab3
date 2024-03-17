package com.example.carlotoledo_antoniochung_comp304sec002_Lab3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule.Schedule
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class AirportScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {

    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

//     Change this in ScheduleDAO.kt
    fun scheduleForStopName(name: String): Flow<List<Schedule>> = scheduleDao.getByAirline(name)
}

class AirportScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirportScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AirportScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
