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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.systems.magora.application.simplemapapplication.model.MapPoint
import kotlin.random.Random

class MyMapViewModel(app: Application) : AndroidViewModel(app) {

    fun getCurrentLocation():MutableLiveData<MapPoint> {
        val locationLiveData=MutableLiveData<MapPoint>()
        FusedLocationProviderClient(getApplication<Application>()).lastLocation.addOnSuccessListener {
        val point=MapPoint(name="My last location",position =LatLng(it.latitude,it.longitude) )
           locationLiveData.postValue(point)
        }.addOnFailureListener {
            Log.d("mylog","Ошибка запроса последнего местоположения ${it.message}")
        }.addOnCompleteListener {
            Log.d("mylog","OnCompleteListener ")
        }
        return locationLiveData
    }

    fun getRandomLocation():MutableList<MapPoint>{
        val points= mutableListOf<MapPoint>()
        val min=-10.0
        val max=10.0

        for(i in 0 until 4){
            val x=Random.nextDouble(min,max)
            val y=Random.nextDouble(min,max)
            points.add(MapPoint("Random Point ${i}", LatLng(x,y)))
        }
        return points
    }

}