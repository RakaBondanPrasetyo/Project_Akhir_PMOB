package com.raka.projectakhir

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DetailKostActivity : AppCompatActivity() {
    private lateinit var imgKost: ImageView
    private lateinit var tvNamaKostDetail: TextView
    private lateinit var tvHargaKostDetail: TextView
    private lateinit var tvDeskripsiKostDetail: TextView
    //private lateinit var tvJarak: TextView
    private lateinit var tvPemilikKostDetail: TextView
    private lateinit var tvNoPemilikKost: TextView
    private lateinit var btnPesanSekarang: Button

    private lateinit var btnMaps: Button
//    private lateinit var rvKost: RecyclerView
    private lateinit var prefs: SharedPreferences

    private lateinit var pesanRef: DatabaseReference

    private lateinit var adapter: KostAdapter
    private var noHpPemilik: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kost)

        prefs = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE)

        // Initialize Views
        imgKost = findViewById(R.id.imgKost)
        tvNamaKostDetail = findViewById(R.id.tvNamaKostDetail)
        tvHargaKostDetail = findViewById(R.id.tvHargaKostDetail)
        tvDeskripsiKostDetail = findViewById(R.id.tvDeskripsiKostDetail)
        tvPemilikKostDetail = findViewById(R.id.tvPemilikKostDetail)
        tvNoPemilikKost = findViewById(R.id.tvNoPemilikKost)
        btnPesanSekarang = findViewById(R.id.btnPesanSekarang)
        btnMaps = findViewById(R.id.btnMaps)
        //rvKost = findViewById(R.id.rvKost)

        pesanRef = FirebaseDatabase.getInstance().getReference("pesanan")

        val nama = intent.getStringExtra("nama") ?: "-"
        val harga = intent.getStringExtra("harga") ?: "-"
        val deskripsi = intent.getStringExtra("deskripsi") ?: "-"
        val jarak= intent.getStringExtra("jarak") ?: "-"
        val fotoResId = intent.getIntExtra("fotoResId", 0)
        val namaPemilik = intent.getStringExtra("namaPemilik") ?: "-"
        val noHpPemilik = intent.getStringExtra("noHpPemilik") ?: "-"
        val detail1 = intent.getStringExtra("detail1") ?: "-"
        val detail2 = intent.getStringExtra("detail2") ?: "-"
        val detail3 = intent.getStringExtra("detail3") ?: "-"
        val detail4 = intent.getStringExtra("detail4") ?: "-"
        val detail5 = intent.getStringExtra("detail5") ?: "-"
        val lat = intent.getDoubleExtra("lat", 0.0)
        val lng = intent.getDoubleExtra("lng", 0.0)

        tvNamaKostDetail.text = nama
        tvHargaKostDetail.text = harga
        tvDeskripsiKostDetail.text = "Deskripsi: $deskripsi"
        //tvJarak.text = "Jarak: $jarak"
        tvPemilikKostDetail.text = "Pemilik: $namaPemilik"
        tvNoPemilikKost.text = "No Hp Pemilik: $noHpPemilik"

        if (fotoResId != 0) {
            imgKost.setImageResource(fotoResId)
        }

        btnPesanSekarang.setOnClickListener {
            val dialog = AddPesananDialog(this, pesanRef)
            dialog.show()
        }

        btnMaps.setOnClickListener {
            val i = Intent(this, MapsActivity::class.java).apply {
                putExtra("lat", lat)
                putExtra("lng", lng)
                putExtra("nama", intent.getStringExtra("nama") ?: "Lokasi Kost")
            }
            startActivity(i)
        }
    }

}

