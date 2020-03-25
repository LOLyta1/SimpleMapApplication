package com.systems.magora.application.simplemapapplication

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_map.view.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class MyMapFragment :
    Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync {
            Log.d("mylog", "карта готова")
            it.mapType=GoogleMap.MAP_TYPE_NORMAL
            it.setOnMarkerClickListener {
                val text="${it?.position?.latitude}.${it?.position?.longitude}"
                Log.d("mylog","нажали на маркер ${it?.position?.latitude}.${it?.position?.longitude}")
                Snackbar.make(view,text,Snackbar.LENGTH_INDEFINITE).apply {
                    this.setAction("Close",{this.dismiss()})
                    this.show()
                }
                true
            }
            val lang=LatLng(55.7558, 37.6173)
          getRandomPoint(3,it)
        }

    }
    fun getRandomPoint(count: Int, map: GoogleMap) {
        for (i in 1..count) {
            map.addMarker(MarkerOptions().position(getRandomCoord())).showInfoWindow()

        }
    }
    private fun getRandomCoord(): LatLng {
        val x = Random.nextDouble(-90.0, 90.0)
        val y = Random.nextDouble(-90.0, 90.0)
        return LatLng(x, y)
    }
}




