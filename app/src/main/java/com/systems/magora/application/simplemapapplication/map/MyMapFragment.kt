package com.systems.magora.application.simplemapapplication.map

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.systems.magora.application.simplemapapplication.MainActivity
import com.systems.magora.application.simplemapapplication.PermissionHelper
import com.systems.magora.application.simplemapapplication.R
import kotlinx.android.synthetic.main.fragment_map.view.*

/**
 * A simple [Fragment] subclass.
 */
class MyMapFragment :
    Fragment(),
    MainActivity.IPermissionListener {

    private var map: GoogleMap? = null
    private lateinit var viewModel: MyMapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(activity!!).get(MyMapViewModel::class.java)
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermissionAndLoad()

        view.target_floating_action_button.setOnClickListener {
            viewModel.getCurrentLocation().observe(viewLifecycleOwner, Observer {
                map?.clear()
                map?.addMarker(MarkerOptions().title(it.name).position(it.position))
                map?.moveCamera(CameraUpdateFactory.newLatLngZoom(it.position, 4.0F))
            })
        }

        view.random_action_button.setOnClickListener {
            viewModel.getRandomLocation().apply {
                map?.clear()
                this.forEach { map?.addMarker(MarkerOptions().title(it.name).position(it.position)) }
                map?.moveCamera(CameraUpdateFactory.newLatLngZoom(this.last().position,4.0F))
            }
        }
    }


    private fun checkPermissionAndLoad() {
        if (PermissionHelper.isPermissionsGranted(activity!!, PermissionHelper.LOCATION)) {
            loadMap()
        } else {
            PermissionHelper.requestPermission(activity!!, PermissionHelper.LOCATION)
        }
    }

    override fun onPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            loadMap()
        } else {
      AlertDialog.Builder(context)
                .setIcon(android.R.drawable.stat_notify_error)
                .setMessage("Выделите права в настройках системы")
                .setPositiveButton(android.R.string.ok,{dialog, which -> checkPermissionAndLoad() })
                .setCancelable(false)
                .create()
                .apply { show() }
        }
    }

    private fun loadMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync {
            map = it
            it.setOnMarkerClickListener {
                Snackbar.make( view!!,"${it.title}\n${it?.position?.latitude};${it?.position?.longitude}",Snackbar.LENGTH_INDEFINITE
                ).apply {
                    this.setAction("Close") { this.dismiss() }
                    this.show()
                }
                true
            }

        }
    }
}




