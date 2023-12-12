package com.example.filmyukol.katalog

import androidx.lifecycle.ViewModel
import com.example.filmyukol.R
import com.example.filmyukol.repositories.FilmsRepository
import kotlinx.coroutines.flow.MutableStateFlow

class FilmDBViewModel : ViewModel() {

    val filmy = MutableStateFlow(FilmsRepository.nactiFilmy())
    val jmenoNovehoFilmu = MutableStateFlow("")
    val rokPremieryNovehoFilmu : MutableStateFlow<Int?> = MutableStateFlow( null)
    val popisNovehoFilmu = MutableStateFlow("")
    val jmenoNovehoRezisera = MutableStateFlow("")
    val naSirku = MutableStateFlow(false)
    val trailerNovehoFilmu = MutableStateFlow("")
    val csfdNovehoFilmu = MutableStateFlow("")

    fun pridejFilm(){
        val maxId =(filmy.value.maxBy { it.id }).id
        val newId = maxId+1
        FilmsRepository.pridejFilm(
            FilmsRepository.Film(
                newId,
                jmenoNovehoFilmu.value,
                popisNovehoFilmu.value,
                jmenoNovehoRezisera.value,
                rokPremieryNovehoFilmu.value,
                listOf("a","b"),
                R.drawable.ic_forrest,
                trailerNovehoFilmu.value,
                csfdNovehoFilmu.value
            )
        )
    }

    fun zmenNaSirku(){
        naSirku.value = !naSirku.value
    }

    fun removeFilm(film : FilmsRepository.Film){
        FilmsRepository.removeFilm(film)
    }


}
