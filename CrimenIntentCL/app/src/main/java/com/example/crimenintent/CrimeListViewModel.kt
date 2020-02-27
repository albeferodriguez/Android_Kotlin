package com.example.crimenintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel: ViewModel(){

    private val crimeRepository = CrimeRepository.get()
    val crimesListLiveData = crimeRepository.getCrimes()

}