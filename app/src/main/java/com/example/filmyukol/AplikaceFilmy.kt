package com.example.filmyukol

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


class AplikaceFilmy : Application() {
    val dataStore: DataStore<Preferences> by preferencesDataStore(name="filmy")

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance : AplikaceFilmy
    }

}