package com.raka.projectakhir

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class TerbaikActivity : AppCompatActivity() {
    private lateinit var etSearch: EditText
    private lateinit var prefs: SharedPreferences
    private lateinit var rvKost: RecyclerView

    private lateinit var tvWelcome: TextView
    private lateinit var btnLogout: Button
    private lateinit var adapter: KostAdapter
    private val listKost = mutableListOf<Kost>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.terbaik_activity)

        prefs = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE)

        tvWelcome = findViewById(R.id.tvWelcome)
        btnLogout = findViewById(R.id.btnLogout)

        val namaUser = prefs.getString(LoginActivity.KEY_NAMA, "Pengguna")
        tvWelcome.text = "Halo, $namaUser"

        btnLogout.setOnClickListener {
            prefs.edit().clear().apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        val cardTerjauh: CardView = findViewById(R.id.cardTerjauh)

        cardTerjauh.setOnClickListener {
            val intent = Intent(this, TerjauhActivity::class.java)
            startActivity(intent)
        }

        val cardTerdekat: CardView = findViewById(R.id.cardTerdekat)

        cardTerdekat.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val cardTermahal: CardView = findViewById(R.id.cardTermahal)

        cardTermahal.setOnClickListener {
            val intent = Intent(this, TermahalActivity::class.java)
            startActivity(intent)
        }

        val cardTermurah: CardView = findViewById(R.id.cardTermurah)

        cardTermurah.setOnClickListener {
            val intent = Intent(this, TermurahActivity::class.java)
            startActivity(intent)
        }


        isiDataKost()

        adapter = KostAdapter(listKost) { kost ->
            val intent = Intent(this, DetailKostActivity::class.java).apply {
                putExtra("nama", kost.nama)
                putExtra("rating", kost.rating)
                putExtra("harga", kost.harga)
                putExtra("deskripsi", kost.desckripsi)
                putExtra("jarak", kost.jarak)
                putExtra("fotoResId", kost.fotoResId)
                putExtra("namaPemilik", kost.namaPemilik)
                putExtra("noHpPemilik", kost.noHpPemilik)
                putExtra("detail1", kost.detail1)
                putExtra("detail2", kost.detail2)
                putExtra("detail3", kost.detail3)
                putExtra("detail4", kost.detail4)
                putExtra("detail5", kost.detail5)
                putExtra("lat", kost.lat)
                putExtra("lng", kost.lng)
            }
            startActivity(intent)
        }
        rvKost = findViewById(R.id.rvKost)
        rvKost.layoutManager = LinearLayoutManager(this)
        rvKost.adapter = adapter
    }

    private fun isiDataKost() {
        listKost.clear()

        listKost.add(
            Kost(
                nama = "Kost Raya",
                fotoResId = R.drawable.kost1,
                rating = "⭐ 4.9",
                jarak = "578m",
                desckripsi ="Kamar kost ini memiliki desain minimalis dan modern yang cocok untuk mahasiswa maupun pekerja kantoran. Interior kamar didominasi warna netral dan lembut, memberikan kesan bersih dan nyaman. Lantai keramik berwarna terang menambah kesan luas pada ruangan.",
                harga = "Rp. 1.650.000 (Bulan Pertama)",
                namaPemilik = "Bu Sari",
                noHpPemilik = "0813285274985",
                detail1 = "• Ruangan 3.5 x 3 Meter",
                detail2 = "• AC",
                detail3 = "• Gantungan Baju",
                detail4 = "• Bantal",
                detail5 = "• Kasur",
                detail6 = "• Meja",
                detail7 = "• Kamar Mandi Dalam",
                detail8 = "• Cermin",
                lat = -7.795580,
                lng = 110.369490
            )
        )

        listKost.add(
            Kost(
                nama = "Kost Arkana",
                fotoResId = R.drawable.kost2,
                rating = "⭐ 4.8",
                jarak = "748m",
                desckripsi = "Kamar kost ini mengusung konsep modern minimalis dengan desain yang elegan dan efisien. Kamar ini memiliki jendela besar yang memungkinkan cahaya alami masuk dengan baik, menciptakan suasana terang dan nyaman. Di bagian depan meja, tersedia televisi dinding yang menambahkan kenyamanan saat bersantai",
                harga = "Rp. 1.500.000 (Bulan Pertama)",
                namaPemilik = "Pak Eko",
                noHpPemilik = "081234567891",
                detail1 = "• Ruangan 3.5 x 3 Meter",
                detail2 = "• AC",
                detail3 = "• Gantungan Baju",
                detail4 = "• Bantal",
                detail5 = "• Kasur",
                detail6 = "• Meja",
                detail7 = "• Kamar Mandi Dalam",
                detail8 = "• Cermin",
                lat = -7.782146,
                lng = 110.364874
            )
        )

        listKost.add(
            Kost(
                nama = "Kost Sirana",
                fotoResId = R.drawable.kost3,
                rating = "⭐ 4.7",
                jarak = "955m",
                desckripsi ="Kamar kost ini memiliki desain minimalis dan modern yang cocok untuk mahasiswa maupun pekerja kantoran. Interior kamar didominasi warna netral dan lembut, memberikan kesan bersih dan nyaman. Lantai keramik berwarna terang menambah kesan luas pada ruangan.",
                harga = "Rp. 1.250.000 (Bulan Pertama)",
                namaPemilik = "Bu Kirana",
                noHpPemilik = "085267871235",
                detail1 = "• Ruangan 3.5 x 3 Meter",
                detail2 = "• AC",
                detail3 = "• Gantungan Baju",
                detail4 = "• Bantal",
                detail5 = "• Kasur",
                detail6 = "• Meja",
                detail7 = "• Kamar Mandi Dalam",
                detail8 = "• Cermin",
                lat = -7.801329,
                lng = 110.360446

            )
        )

        listKost.add(
            Kost(
                nama = "Kost Putri Athena",
                fotoResId = R.drawable.kost4,
                rating = "⭐ 4.6",
                jarak = "1178m",
                desckripsi ="Kamar kost ini mengusung desain minimalis dan modern yang sangat cocok untuk mahasiswa maupun pekerja kantoran. Interior kamar didominasi oleh warna netral yang lembut, memberikan kesan yang bersih dan nyaman. Lantai keramik berwarna terang semakin mempertegas kesan luas pada ruangan, menciptakan suasana yang lapang dan nyaman.",
                harga = "Rp. 1.410.000 (Bulan Pertama)",
                namaPemilik = "Pak Sutarman",
                noHpPemilik = "082907654189",
                detail1 = "• Ruangan 3.5 x 3 Meter",
                detail2 = "• AC",
                detail3 = "• Gantungan Baju",
                detail4 = "• Bantal",
                detail5 = "• Kasur",
                detail6 = "• Meja",
                detail7 = "• Kamar Mandi Dalam",
                detail8 = "• Cermin",
                lat = -7.784903,
                lng = 110.357435
            )
        )

        listKost.add(
            Kost(
                nama = "Kost Putri Aphrodite",
                fotoResId = R.drawable.kost5,
                rating = "⭐ 4.5",
                jarak = "1478m",
                desckripsi ="Kamar kost ini mengusung desain minimalis dan modern yang sangat cocok untuk mahasiswa maupun pekerja kantoran. Interior kamar didominasi oleh warna netral yang lembut, memberikan kesan yang bersih dan nyaman. Lantai keramik berwarna terang semakin mempertegas kesan luas pada ruangan, menciptakan suasana yang lapang dan nyaman.",
                harga = "Rp. 1.370.000 (Bulan Pertama)",
                namaPemilik = "Bu Minarsih",
                noHpPemilik = "081367892345",
                detail1 = "• Ruangan 3.5 x 3 Meter",
                detail2 = "• AC",
                detail3 = "• Gantungan Baju",
                detail4 = "• Bantal",
                detail5 = "• Kasur",
                detail6 = "• Meja",
                detail7 = "• Kamar Mandi Dalam",
                detail8 = "• Cermin",
                lat = -7.795043,
                lng = 110.357028
            )
        )
    }

}
