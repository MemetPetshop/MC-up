package com.example.matematika_cer

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.matematika_cer.model.User
import com.example.matematika_cer.network.ApiClient
import com.example.matematika_cer.network.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etKelas: EditText
    private lateinit var spinnerRole: Spinner
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNama = findViewById(R.id.namalengkapRegis)
        etUsername = findViewById(R.id.namapenggunaRegis)
        etPassword = findViewById(R.id.katasandiRegislay)
        etKelas = findViewById(R.id.kelas)
        spinnerRole = findViewById(R.id.spinnerRole)
        btnRegister = findViewById(R.id.tombolRegis)

        btnRegister.setOnClickListener {
            val user = User(
                namaLengkap = etNama.text.toString(),
                username = etUsername.text.toString(),
                password = etPassword.text.toString(),
                role = spinnerRole.selectedItem.toString().lowercase(),
                kelas = etKelas.text.toString()
            )

            val api = ApiClient.instance.create(UserApi::class.java)
            api.register(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "Register berhasil!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Gagal: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
