package com.example.crimenintent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.crimenintent.Crime

@Database(entities= [Crime::class], version = 1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDataBase: RoomDatabase(){

    abstract fun crimeDAO(): CrimeDAO

}