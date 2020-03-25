package com.systems.magora.application.simplemapapplication

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.systems.magora.application.simplemapapplication.map.MyMapFragment

class MainActivity : FragmentActivity() {

    interface IPermissionListener {
        fun onPermissionResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.conttainer_fragment,
                MyMapFragment(), MyMapFragment::class.java.name)
            .commit()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val fragment = supportFragmentManager.findFragmentByTag(MyMapFragment::class.java.name)
        if (fragment != null) {
            (fragment as IPermissionListener).onPermissionResult(
                requestCode,
                permissions,
                grantResults
            )
        }
    }
}
