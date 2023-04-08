package com.example.midtronics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midtronics.adapters.CountryAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var profileFragment: ProfileFragment
    lateinit var countriesFragment: CountriesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BottomNavigation : BottomNavigationView = findViewById(R.id.btm_nav)
        val toolbar_title: TextView = findViewById(R.id.toolbar_title)
        countriesFragment = CountriesFragment()
        toolbar_title.text = "Countries"
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame_layout,countriesFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        BottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_countries->{
                    toolbar_title.text = "Countries"
                    countriesFragment = CountriesFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame_layout,countriesFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.nav_profile->{
                    toolbar_title.text = "Profile"
                    profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame_layout,profileFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }
}