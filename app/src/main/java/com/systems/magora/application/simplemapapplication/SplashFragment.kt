package com.systems.magora.application.simplemapapplication

import android.content.Context.LOCATION_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.fragment_splash.view.*


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.go_to_map_button.setOnClickListener {
            //val action = SplashFragmentDirections.actionSplashFragmentToMapFragment()
            //findNavController().navigate(action)
        }
    }

}
