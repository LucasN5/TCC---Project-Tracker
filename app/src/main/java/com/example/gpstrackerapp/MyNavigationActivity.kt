package com.example.gpstrackerapp

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.gpstrackerapp.databinding.ActivityMyNavigationBinding
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.firebase.database.DatabaseError

class MyNavigationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMyNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val sydney = LatLng(-23.9, -46.3)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").icon(bitmapFromDrawable(mMap, R.drawable.baseline_location_user)))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
    private fun bitmapFromDrawable(
        context: GoogleMap, imageID: Int): BitmapDescriptor {
        // Criar um drawable a partir do ID da imagem.
        val imageDrawable: Drawable? = ContextCompat.getDrawable(this, imageID)

        // Definir os limites do drawable vetorial.
        imageDrawable?.setBounds(0, 0, imageDrawable.intrinsicWidth, imageDrawable.intrinsicHeight)

        // Criar um bitmap para o drawable.
        val bitmap = Bitmap.createBitmap(
            imageDrawable?.intrinsicWidth ?: 0,
            imageDrawable?.intrinsicHeight ?: 0,
            Bitmap.Config.ARGB_8888
        )

        // Adicionar o bitmap ao canvas.
        val canvas = Canvas(bitmap)

        // Desenhar o drawable vetorial no canvas.
        imageDrawable?.draw(canvas)

        // Retornar o BitmapDescriptor a partir do bitmap gerado.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }
}