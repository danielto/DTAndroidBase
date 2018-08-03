package cz.tokar.android.app.myarchitecture1.ui.presenter.favorite

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import cz.tokar.android.app.myarchitecture1.R
import cz.tokar.android.app.myarchitecture1.ui.presenter.base.BaseFragment
import cz.tokar.android.app.myarchitecture1.ui.presenter.main.MainFragment
import kotlinx.android.synthetic.main.frg_favorite.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber
import cz.tokar.android.app.myarchitecture1.R.id.mapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MarkerOptions




class FavoriteFragment : BaseFragment(), OnMapReadyCallback {

  companion object {
    val TAG = FavoriteFragment::class.java.simpleName
    fun newInstance() = FavoriteFragment()
  }

  private lateinit var viewModel: FavoriteViewModel
  private var mGoogleMap: GoogleMap? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    Timber.v("[%s]::[onCreateView]", MainFragment.TAG)
    val view = inflater.inflate(R.layout.frg_favorite, container, false)

    return view
  }


  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoriteViewModel::class.java)
    setupToolbar(myToolbar, R.string.toolbar_favorite)
    setupMapView()
  }

  // mapView needs all life cycle methods from fragment to by forwarded
  override fun onStart() {
    super.onStart()
    mapView?.onStart()
  }

  override fun onResume() {
    super.onResume()
    mapView?.onResume()
  }

  override fun onPause() {
    super.onPause()
    mapView?.onPause()
  }

  override fun onStop() {
    super.onStop()
    mapView?.onStop()
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView?.onDestroy()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    mapView?.onSaveInstanceState(outState)
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView?.onLowMemory()
  }

  override fun onMapReady(googleMap: GoogleMap?) {
    mGoogleMap = googleMap
    mGoogleMap?.apply {
      uiSettings.isMapToolbarEnabled = false
      uiSettings.isMyLocationButtonEnabled = true
      val brno = LatLng(49.194974, 16.608103)
      addMarker(MarkerOptions().position(brno).title("Brno"))
      moveCamera(CameraUpdateFactory.newLatLng(brno))
      animateCamera( CameraUpdateFactory.zoomTo( 18.0f ) )
    }
//    MapsInitializer.initialize(context!!) // only if features need to be used before obtaining a map
  }

  private fun setupMapView(){
    mapView.onCreate(null)
    mapView.getMapAsync(this)
  }

}
