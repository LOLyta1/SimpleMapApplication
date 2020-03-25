package com.systems.magora.application.simplemapapplication

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity

class PermissionHelper {

    companion object {
        private val LOCATION_PERMISSION_REQUEST_CODE = 1

        val LOCATION = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        fun isPermissionsGranted(activity: FragmentActivity, permissions: Array<String>): Boolean {
            var isAllGranted = true
            permissions.forEach {
                if (ActivityCompat.checkSelfPermission(activity,it) != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false
                }
            }
            return isAllGranted
        }

        fun requestPermission(activity: FragmentActivity, permissions: Array<String>) {
            ActivityCompat.requestPermissions(
                activity,
                permissions,
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }
}


