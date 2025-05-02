package com.example.matematika_cer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.matematika_cer.model.Topik
import com.example.matematika_cer.model.Soal
import com.example.matematika_cer.network.ApiClient
import com.example.matematika_cer.network.TopikApi
import com.example.matematika_cer.network.SoalApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopikActivity : AppCompatActivity() {

    private lateinit var etNamaTopik: EditText
    private lateinit var etDeskripsi: EditText
    private lateinit var etJumlahSoal: EditText
    private lateinit var etDurasi: EditText
    private lateinit var btnSimpanTopik: Button

    private lateinit var etPertanyaan: EditText
    private lateinit var etA: EditText
    private lateinit var etB: EditText
    private lateinit var etC: EditText
    private lateinit var etD: EditText
    private lateinit var rgJawaban: RadioGroup
    private lateinit var btnSoalBerikut: Button

    private var idTopikBaru: Long = 1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_buat_topikdan_soal)

        // Layout inflater untuk bagian soal
        val soalLayout = LayoutInflater.from(this).inflate(R.layout.fragment_buat_soal, null)

        // Komponen input topik (dari layout utama)
        etNamaTopik = findViewById(R.id.etNamaTopik)
        etDeskripsi = findViewById(R.id.etDeskripsiTopik)
        etJumlahSoal = findViewById(R.id.etJumlahSoal)
        etDurasi = findViewById(R.id.etDurasi)
        btnSimpanTopik = findViewById(R.id.btnKonfirmasi)

        // Komponen input soal (dari fragment soal)
        etPertanyaan = soalLayout.findViewById(R.id.etPertanyaan)
        etA = soalLayout.findViewById(R.id.etA)
        etB = soalLayout.findViewById(R.id.etB)
        etC = soalLayout.findViewById(R.id.etC)
        etD = soalLayout.findViewById(R.id.etD)
        rgJawaban = soalLayout.findViewById(R.id.rgJawaban)
        btnSoalBerikut = soalLayout.findViewById(R.id.btnSoalBerikut)

        // Tombol simpan topik
        btnSimpanTopik.setOnClickListener {
            val topik = Topik(
                namaTopik = etNamaTopik.text.toString(),
                deskripsiTopik = etDeskripsi.text.toString(),
                jumlahSoal = etJumlahSoal.text.toString(),
                durasiMenit = etDurasi.text.toString()
            )

            val api = ApiClient.instance.create(TopikApi::class.java)
            api.buatTopik(topik).enqueue(object : Callback<Topik> {
                override fun onResponse(call: Call<Topik>, response: Response<Topik>) {
                    if (response.isSuccessful) {
                        val topikBaru = response.body()
                        idTopikBaru = topikBaru?.id ?: 1L
                        Toast.makeText(this@TopikActivity, "Topik berhasil disimpan", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@TopikActivity, "Gagal simpan topik", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Topik>, t: Throwable) {
                    Toast.makeText(this@TopikActivity, "Gagal koneksi: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        // Tombol simpan soal
        btnSoalBerikut.setOnClickListener {
            val selectedJawaban = when (rgJawaban.checkedRadioButtonId) {
                R.id.rbA -> "A"
                R.id.rbB -> "B"
                R.id.rbC -> "C"
                R.id.rbD -> "D"
                else -> ""
            }

            if (selectedJawaban.isEmpty()) {
                Toast.makeText(this, "Pilih jawaban yang benar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val soal = Soal(
                pertanyaan = etPertanyaan.text.toString(),
                opsiA = etA.text.toString(),
                opsiB = etB.text.toString(),
                opsiC = etC.text.toString(),
                opsiD = etD.text.toString(),
                jawaban = selectedJawaban,
                topik = Topik(id = idTopikBaru, namaTopik = "", deskripsiTopik = "", jumlahSoal = "", durasiMenit = "")
            )

            val api = ApiClient.instance.create(SoalApi::class.java)
            api.buatSoal(soal).enqueue(object : Callback<Soal> {
                override fun onResponse(call: Call<Soal>, response: Response<Soal>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@TopikActivity, "Soal berhasil disimpan", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@TopikActivity, "Gagal simpan soal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Soal>, t: Throwable) {
                    Toast.makeText(this@TopikActivity, "Gagal konek: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
