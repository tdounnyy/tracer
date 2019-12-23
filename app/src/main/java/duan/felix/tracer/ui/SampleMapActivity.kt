package duan.felix.tracer.ui

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import util.Consts


class SampleMapActivity : AppCompatActivity(), OnMapReadyCallback {
  private var mLastKnownLocation: Location? = null
  private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
  private lateinit var mPlaceClient: PlacesClient

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // Retrieve the content view that renders the map.
    setContentView(duan.felix.tracer.R.layout.activity_map)
    // Get the SupportMapFragment and request notification
    // when the map is ready to be used.
    val mapFragment = supportFragmentManager
      .findFragmentById(duan.felix.tracer.R.id.map) as SupportMapFragment?
    mapFragment!!.getMapAsync(this)

    // Construct a PlaceDetectionClient.
    Places.initialize(applicationContext, Consts.GOOGLE_API_KEY)
    mPlaceClient = Places.createClient(this)

    mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
  }

  override fun onMapReady(googleMap: GoogleMap?) {
    Log.d("felixx", "MapActivity onMapReady")
    if (googleMap != null) {
      showSelf(googleMap)
      showSydney(googleMap)
    }
  }

  private fun showSelf(googleMap: GoogleMap) {
    // Do other setup activities here too, as described elsewhere in this tutorial.

    // Turn on the My Location layer and the related control on the map.
    updateLocationUI(googleMap)
    // Get the current location of the device and set the position of the map.
    getDeviceLocation(googleMap);
  }

  private fun updateLocationUI(googleMap: GoogleMap) {
    try {
      googleMap.setMyLocationEnabled(true)
      googleMap.getUiSettings().setMyLocationButtonEnabled(true)
    } catch (e: SecurityException) {
      Log.e("Exception: %s", e.message)
    }
  }

  private fun getDeviceLocation(mMap: GoogleMap) {
    /*
     * Get the best and most recent location of the device, which may be null in rare
     * cases when a location is not available.
     */
    try {
      val locationResult = mFusedLocationProviderClient.lastLocation
      locationResult.addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
          // Set the map's camera position to the current location of the device.
          mLastKnownLocation = task.result
          mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
              mLastKnownLocation?.let {
                LatLng(it.latitude, it.longitude)
              }
              , Consts.DEFAULT_ZOOM)
          )
        } else {
          Log.d("felixx", "Current location is null. Using defaults.")
          Log.e("felixx", "Exception: %s", task.exception)
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(0.0, 0.0), Consts.DEFAULT_ZOOM))
          mMap.getUiSettings().setMyLocationButtonEnabled(false)
        }
      }
    } catch (e: SecurityException) {
      Log.e("Exception: %s", e.message)
    }
  }

  private fun showSydney(googleMap: GoogleMap) {
    // Add a marker in Sydney, Australia,
    // and move the map's camera to the same location.
    val sydney = LatLng(-33.852, 151.211)
    googleMap.addMarker(
      MarkerOptions().position(sydney)
        .title("Marker in Sydney")
    )
    googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
  }
}