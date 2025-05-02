package com.example.matematika_cer.loginregis

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.matematika_cer.R

class LoginActivity : AppCompatActivity() {

    private lateinit var inputNama: EditText
    private lateinit var inputPassword: EditText
    private lateinit var spinnerKelas: Spinner
    private lateinit var btnLogin: Button
    private lateinit var btnRegis: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi view
        inputNama = findViewById(R.id.inputname)
        inputPassword = findViewById(R.id.inputPassword)
        spinnerKelas = findViewById(R.id.kelas)
        btnLogin = findViewById(R.id.tombol_login)
        btnRegis = findViewById(R.id.tombol_regis)

        val prefs = getSharedPreferences("user_data", MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val nama = inputNama.text.toString().trim()
            val password = inputPassword.text.toString().trim()
            val kelas = spinnerKelas.selectedItem.toString()

            if (nama.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Nama dan kata sandi harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (kelas == "Pilih kelas") {
                Toast.makeText(this, "Pilih kelas terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val storedPassword = prefs.getString("${nama}_password", null)
            val storedRole = prefs.getString("${nama}_role", null)
            val storedKelas = prefs.getString("${nama}_kelas", null)

            if (storedPassword == null || storedRole == null) {
                Toast.makeText(this, "Akun tidak ditemukan.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isGuru = storedRole == "guru"
            val isSiswa = storedRole == "siswa"

            if (password != storedPassword) {
                Toast.makeText(this, "Kata sandi salah", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isSiswa && storedKelas != kelas) {
                Toast.makeText(this, "Kelas tidak cocok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Login berhasil
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("nama", nama)
                putExtra("role", storedRole)
                if (isSiswa) putExtra("kelas", kelas)
            }

            Toast.makeText(this, "Login berhasil sebagai ${storedRole.capitalize()}", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        }

        btnRegis.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
