package com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Schedule(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "Airline") val airline: String,
    @ColumnInfo(name = "ArrivalTime") val arrivalTime: String,
    @ColumnInfo(name = "Terminal") val terminal: String,
    @ColumnInfo(name = "Destination") val destination: String,
    @ColumnInfo(name = "Status") val status: String
)

