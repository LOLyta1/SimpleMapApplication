package com.systems.magora.application.simplemapapplication.model

import com.google.android.gms.maps.model.LatLng
import java.text.FieldPosition

data class MapPoint (
    var name:String="",
    var position: LatLng=LatLng(0.0,0.0)
)