package com.systems.magora.application.simplemapapplication.map

import android.Manifest
import android.app.Application
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

class MyMapViewModel(app: Application) : AndroidViewModel(app) {

    fun getCurrentLocation():MutableLiveData<LatLng> {
        val locationLiveData=MutableLiveData<LatLng>()
        FusedLocationProviderClient(getApplication<Application>()).lastLocation.addOnSuccessListener {
           locationLiveData.postValue(LatLng(it.latitude,it.longitude))
        }.addOnFailureListener {
            Log.d("mylog","Ошибка запроса последнего местоположения ${it.message}")
        }.addOnCompleteListener {
            Log.d("mylog","OnCompleteListener ")
        }
        return locationLiveData
    }

    fun getRandomLocation(){

    }
}