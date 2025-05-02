package com.example.matematika_cer.loginregis

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.matematika_cer.R
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var namaLengkap: EditText
    private lateinit var namaPengguna: EditText
    private lateinit var kataSandi: EditText
    private lateinit var kelasSpinner: Spinner
    private lateinit var kodeAkses: EditText
    private lateinit var kodeLayout: TextInputLayout
    private lateinit var radioGuru: RadioButton
    private lateinit var radioSiswa: RadioButton
    private lateinit var tombolDaftar: Button
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        namaLengkap = findViewById<TextInputLayout>(R.id.namalengkapRegis).editText!!
        namaPengguna = findViewById<TextInputLayout>(R.id.namapenggunaRegis).editText!!
        kataSandi = findViewById(R.id.katasandiRegis)
        kelasSpinner = findViewById(R.id.kelas)
        kodeLayout = findViewById(R.id.inputKodeRegis)
        kodeAkses = findViewById(R.id.kodeRegis)
        radioGuru = findViewById(R.id.RegisGuru)
        radioSiswa = findViewById(R.id.RegisSiswa)
        tombolDaftar = findViewById(R.id.tombolRegis)

        prefs = getSharedPreferences("user_data", MODE_PRIVATE)

        // Tampilkan input kode hanya jika guru dipilih
        val radioGroup = findViewById<RadioGroup>(R.id.radiogrupRegis)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            kodeLayout.visibility = if (checkedId == R.id.RegisGuru) View.VISIBLE else View.GONE
        }
        kodeLayout.visibility = if (radioGuru.isChecked) View.VISIBLE else View.GONE

        tombolDaftar.setOnClickListener {
            val nama = namaLengkap.text.toString().trim()
            val username = namaPengguna.text.toString().trim()
            val sandi = kataSandi.text.toString().trim()
            val kelas = kelasSpinner.selectedItem.toString()
            val kode = kodeAkses.text.toString().trim()
            val role = if (radioGuru.isChecked) "guru" else "siswa"

            // Validasi input kosong
            if (nama.isEmpty() || username.isEmpty() || sandi.isEmpty() || kelas == "Pilih kelas") {
                Toast.makeText(this, "Semua field wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validasi kode akses guru
            val kodeAksesValid = "GURU123"
            if (role == "guru") {
                if (kode.isEmpty()) {
                    Toast.makeText(this, "Masukkan kode akses guru", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (kode != kodeAksesValid) {
                    Toast.makeText(this, "Kode akses guru salah!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Simpan data berdasarkan username
            val editor = prefs.edit()
            editor.putString("${username}_password", sandi)
            editor.putString("${username}_kelas", kelas)
            editor.putString("${username}_role", role)
            if (role == "guru") {
                editor.putString("${username}_kode", kode)
            }
            editor.apply()

            Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
