package com.example.filmyukol.detail

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmyukol.AplikaceFilmy
import com.example.filmyukol.repositories.FilmsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailFilmuViewModel(savedState : SavedStateHandle) : ViewModel() {
    val film = MutableStateFlow<FilmsRepository.Film?>(null)

    val idFilmu: Long? = savedState["idFilmu"]
    val klicOblibenosti = booleanPreferencesKey("oblibenostFilmu" +idFilmu)

    val oblibeny: Flow<Boolean> = AplikaceFilmy.instance.dataStore.data.map{ preferences -> preferences[klicOblibenosti] ?: false}

    fun nactiFilm(idFilmu: Long?){
        film.value = FilmsRepository.nactiFilm(idFilmu)
    }

    fun pridejDoOblibenych(){
        viewModelScope.launch {
            AplikaceFilmy.instance.dataStore.edit { preferences ->
                preferences.set(klicOblibenosti, true)
            }
        }
    }

    fun odeberZOblibenych(){
        viewModelScope.launch {
            AplikaceFilmy.instance.dataStore.edit { preferences ->
                preferences.set(klicOblibenosti, false)
            }
        }
    }
}