package com.raka.projectakhir

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.raka.projectakhir.databinding.UploadDialogBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.let
import kotlin.text.isEmpty
import kotlin.toString


class AddPesananDialog(
    private val context: Context,
    private val pesanRef: DatabaseReference
) {

    fun show() {
        val dialogBinding = UploadDialogBinding.inflate(LayoutInflater.from(context))

        dialogBinding.editTextMenempati.setOnClickListener {
            val calendar = Calendar.getInstance()
            val currentDate = Calendar.getInstance()

            val year = currentDate.get(Calendar.YEAR)
            val month = currentDate.get(Calendar.MONTH)
            val day = currentDate.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(selectedYear, selectedMonth, selectedDay)

                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                dialogBinding.editTextMenempati.setText(dateFormat.format(selectedCalendar.time))
            }, year, month, day)

            datePickerDialog.datePicker.minDate = currentDate.timeInMillis
            datePickerDialog.show()
        }

        MaterialAlertDialogBuilder(context)
            .setTitle("Tambah pengguna")
            .setView(dialogBinding.root)
            .setPositiveButton("Tambah") { dialog, _ ->

                val name = dialogBinding.editTextTitleNama.text.toString()
                val phone = dialogBinding.editTextNoHp.text.toString()
                val email = dialogBinding.editTextEmail.text.toString()
                val releaseDate = dialogBinding.editTextMenempati.text.toString()

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || releaseDate.isEmpty()) {
                    Toast.makeText(context, "Isi semua data!", Toast.LENGTH_SHORT).show()
                } else {
                    saveDataToFirebase(name, phone, email, releaseDate)
                }
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun saveDataToFirebase(name: String, phone: String, email: String, releaseDate: String) {
        val id = pesanRef.push().key
        val newPesan = Pesan(name, phone, email, releaseDate)

        id?.let {
            pesanRef.child(it).setValue(newPesan)
                .addOnSuccessListener {
                    Toast.makeText(context, "Data berhasil ditambah!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                }
                .addOnFailureListener { error ->
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
        }
    }


}

