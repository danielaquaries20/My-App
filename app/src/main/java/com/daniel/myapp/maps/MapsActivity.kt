package com.daniel.myapp.maps

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.crocodic.core.base.activity.CoreActivity
import com.crocodic.core.extension.checkLocationPermission
import com.daniel.myapp.R
import com.daniel.myapp.app_tour.ui.home.HomeViewModel
import com.daniel.myapp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsActivity : CoreActivity<ActivityMapsBinding, HomeViewModel>(R.layout.activity_maps) {

    var myLoc = LatLng(-7.1156443,110.4010885)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_maps)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.mapView.onCreate(savedInstanceState)

        checkLocationPermission {
            listenLocationChange()
        }

        binding.mapView.getMapAsync { googleMap ->
            googleMap.addMarker(
                MarkerOptions()
                    .position(myLoc)
                    .title("My Mark")
            )
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 36f))
        }

    }


    override fun retrieveLocationChange(location: Location) {
        super.retrieveLocationChange(location)
        Log.d("deviceLocation", "latitude: ${location.latitude}, longitude: ${location.longitude}")
    }


    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)

    }

}