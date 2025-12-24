package com.raka.projectakhir

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            val i = Intent(this, DetailKostActivity::class.java)
            intent.extras?.let { i.putExtras(it) } // bawa semua data yang diterima Maps
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(i)
            finish()
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val lat = intent.getDoubleExtra("lat", 0.0)
        val lng = intent.getDoubleExtra("lng", 0.0)
        val nama = intent.getStringExtra("nama") ?: "Lokasi"

        val lokasi = LatLng(lat, lng)
        mMap.addMarker(MarkerOptions().position(lokasi).title(nama))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 16f))
    }
}
