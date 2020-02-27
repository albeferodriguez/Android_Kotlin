package com.example.crimenintent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.crimenintent.database.CrimeDataBase_Impl
import com.example.crimenintent.database.CrimeRepository

class CrimeListViewModel: ViewModel(){

    private val crimeRepository = CrimeRepository.get()
    val crimes = crimeRepository.getCrimes()

}