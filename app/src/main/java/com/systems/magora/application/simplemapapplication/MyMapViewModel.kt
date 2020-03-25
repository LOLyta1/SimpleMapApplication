package com.systems.magora.application.simplemapapplication

import android.app.Application
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.GoogleMap

class MyMapViewModel(app:Application): AndroidViewModel(app) {

    fun getCurrentLocation(map:GoogleMap){
        val app=getApplication<Application>()
        app.getSystemService(LOCATION_SERVICE)
    }
}