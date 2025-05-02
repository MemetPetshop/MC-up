package com.example.matematika_cer.loginregis

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.matematika_cer.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val role = intent.getStringExtra("role")
        val nama = intent.getStringExtra("nama")
        Log.d("MAIN_ACTIVITY", "Role diterima: $role")

        val navGraph = navController.navInflater.inflate(R.navigation.navigasi)
        val startDestinationId = when (role?.lowercase()) {
            "guru" -> R.id.berandaGuruFragment
            "siswa" -> R.id.berandaSiswaFragment
            else -> R.id.berandaGuruFragment
        }
        navGraph.setStartDestination(startDestinationId)

        val bundle = Bundle().apply {
            putString("nama", nama)
        }
        navController.setGraph(navGraph, bundle)

        val bottomNav = findViewById<BottomNavigationView>(R.id.navbar)
        bottomNav.menu.clear()

        when (role?.lowercase()) {
            "guru" -> {
                bottomNav.inflateMenu(R.menu.menu_navbar)
                Log.d("MAIN_ACTIVITY", "Menu guru berhasil dimuat")
            }
            "siswa" -> {
                bottomNav.inflateMenu(R.menu.menu_navbar_siswa)
                Log.d("MAIN_ACTIVITY", "Menu siswa berhasil dimuat")
            }
            else -> {
                Log.d("MAIN_ACTIVITY", "Role tidak dikenali, default menu tidak dimuat")
            }
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // Menu guru
                R.id.beranda_navbar -> {
                    navController.navigate(R.id.berandaGuruFragment)
                    true
                }
                R.id.daftar_siswa_navbar -> {
                    navController.navigate(R.id.daftarsiswaFragment)
                    true
                }
                R.id.nilai_siswa_navbar -> {
                    navController.navigate(R.id.nilaisiswaFragment)
                    true
                }
                // Menu siswa
                R.id.beranda_siswa_navbar -> {
                    navController.navigate(R.id.berandaSiswaFragment)
                    true
                }
                R.id.riwayat_kuis_navbar_siswa -> {
                    navController.navigate(R.id.riwayatkuissiswaFragment)
                    true
                }
                else -> false
            }
        }
    }
}
