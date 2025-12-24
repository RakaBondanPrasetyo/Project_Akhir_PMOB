package com.raka.projectakhir

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java
import kotlin.text.isEmpty
import kotlin.text.trim

class LoginActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var etNoHp: EditText
    private lateinit var btnLogin: Button
    private lateinit var prefs: SharedPreferences

    companion object {
        const val PREFS_NAME = "user_prefs"
        const val KEY_NAMA = "nama"
        const val KEY_EMAIL = "email"
        const val KEY_NOHP = "nohp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        val namaTersimpan = prefs.getString(KEY_NAMA, null)
        if (namaTersimpan != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        etNama = findViewById(R.id.etNama)
        etEmail = findViewById(R.id.etEmail)
        etNoHp = findViewById(R.id.etNoHp)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val noHp = etNoHp.text.toString().trim()

            if (nama.isEmpty() || email.isEmpty() || noHp.isEmpty()) {
                Toast.makeText(this, "Lengkapi semua data", Toast.LENGTH_SHORT).show()
            } else {
                prefs.edit()
                    .putString(KEY_NAMA, nama)
                    .putString(KEY_EMAIL, email)
                    .putString(KEY_NOHP, noHp)
                    .apply()

                Toast.makeText(this, "Data tersimpan. Selamat datang, $nama", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
