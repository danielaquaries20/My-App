package com.daniel.myapp.maps

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.crocodic.core.base.activity.CoreActivity
import com.crocodic.core.extension.checkLocationPermission
import com.daniel.myapp.R
import com.daniel.myapp.app_tour.ui.home.HomeViewModel
import com.daniel.myapp.databinding.ActivityMapsBinding
import com.daniel.myapp.maps.helper.AddressHelper
import com.google.android.gms.maps.model.LatLng
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.pm.PackageManager
import com.google.android.gms.maps.model.CircleOptions
import com.crocodic.core.helper.LocationHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.core.graphics.toColorInt
import com.google.android.gms.maps.CameraUpdateFactory

@AndroidEntryPoint
class MapsActivity : CoreActivity<ActivityMapsBinding, HomeViewModel>(R.layout.activity_maps) {

    @Inject
    lateinit var addressHelper: AddressHelper

    //-7.1179884, 110.3951523
    //-6.9826794, 110.4064857
    var theLoc = LatLng( -7.1179884, 110.3951523)

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
            //Map 1 - Marker
            /*googleMap.addMarker(
                MarkerOptions()
                    .position(myLoc)
                    .title("My Mark")
            )
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 36f))*/

            //Map 2 - Camera Listener Geocoder
            /*googleMap.setOnCameraMoveListener {
                binding.ivTarget.alpha = 0.5f
            }

            googleMap.setOnCameraIdleListener {
                binding.ivTarget.alpha = 1f

                val currentLocation = googleMap.cameraPosition.target

                binding.tvLocation.text =
                    "Lat: ${currentLocation.latitude}\nLng: ${currentLocation.longitude}"

                addressHelper.getAddress(
                    LatLng(
                        currentLocation.latitude,
                        currentLocation.longitude
                    )
                ) {
                    binding.tvAddress.text = it
                }
            }*/

            //Map 3 - Geofence
            // -7.1179884, 110.3951523 -> LatLng 1
            // -7.8266, 112.0110 -> LatLng 2
            // -6.9826794,110.4064857
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@getMapAsync
            }
            googleMap.isMyLocationEnabled = true

            val area = googleMap.addCircle(
                CircleOptions()
                    .center(LatLng(theLoc.latitude, theLoc.longitude))
                    .radius(1_000.0)
                    .strokeColor("#FFC80000".toColorInt())
                    .fillColor("#25C80000".toColorInt())
                    .visible(true)
            )
        }

    }

    private fun isInsideLocation(area: LatLng, position: LatLng): Boolean {
        val loc = LocationHelper.distance(area, position)
        Log.d("deviceLocation", "Jarak $loc")
        return loc < 1
    }


    override fun retrieveLocationChange(location: Location) {
        super.retrieveLocationChange(location)
//        Log.d("deviceLocation", "latitude: ${location.latitude}, longitude: ${location.longitude}")
        //Map 3 - Geofence
        binding.mapView.getMapAsync { googleMap ->
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        location.latitude,
                        location.longitude
                    ), 13f
                )
            )

            val isInside = isInsideLocation(
                LatLng(theLoc.latitude, theLoc.longitude),
                LatLng(location.latitude, location.longitude)
            )

            val status = if (isInside) {
                "dalam"
            } else {
                "luar"
            }

            binding.tvStatus.text = "Kamu berada di $status area."
        }
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