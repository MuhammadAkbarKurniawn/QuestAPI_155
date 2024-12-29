package com.example.datasiswajaringan

import android.app.Application
import com.example.datasiswajaringan.dependenciesinjection.AppContainer
import com.example.datasiswajaringan.dependenciesinjection.MahasiswaContainer

class MahasiswaApplications:Application() {
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = MahasiswaContainer()
    }
}