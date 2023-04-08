package com.example.midtronics

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.midtronics.repository.CountryDetailRepository
import com.example.midtronics.viewmodel.CountryDeatilViewModelFactory
import com.example.midtronics.viewmodel.CountryViewModel
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class CountryDetail : AppCompatActivity() {
    private lateinit var viewModel: CountryViewModel
    private val TAG = "CountryDetails"
    lateinit var capitalName: TextView
    lateinit var population:TextView
    lateinit var  area:TextView
    lateinit var region:TextView
    lateinit var subRegion:TextView
    lateinit var swipe_to_refresh : SwipeRefreshLayout
    lateinit var countryCard : CardView
    lateinit var repository: CountryDetailRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)
        capitalName = findViewById(R.id.capital_name)
        population = findViewById(R.id.population)
        area = findViewById(R.id.area)
        region = findViewById(R.id.area)
        subRegion = findViewById(R.id.sub_region)
        swipe_to_refresh = findViewById<SwipeRefreshLayout>(R.id.swipe_to_refresh)
        countryCard = findViewById<CardView>(R.id.country_card)
        repository = CountryDetailRepository()
        val modelFactory = repository?.let {CountryDeatilViewModelFactory(it)}
        viewModel = ViewModelProviders.of(this,modelFactory).get(CountryViewModel::class.java)
        intent.getStringExtra("country")?.let {
            if(verifyAvailableNetwork(this) == true){
                viewModel?.getCountryDetail(it)
                countryCard.visibility = View.VISIBLE
            }else{
                swipe_to_refresh.isRefreshing = true
                countryCard.visibility = View.GONE
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
                val snackbar = Snackbar.make(swipe_to_refresh,"No Internet Connection",Snackbar.LENGTH_LONG)
                snackbar.setAction("retry", View.OnClickListener {
                    checkInternet()
                })
                snackbar.show()
            }
        }
        viewModel?.loading?.observe(this, Observer {
            if(it == true){
                swipe_to_refresh.isRefreshing = true
            }else{
                swipe_to_refresh.isRefreshing = false
            }
        })



        viewModel?.liveResult?.observe(this, Observer {
            it?.let {
                capitalName?.text = it?.get(0)?.name?.official
                population?.text = it?.get(0)?.population.toString()
                area?.text = it?.get(0)?.area?.toString()
                region?.text = it?.get(0)?.region
                subRegion?.text = it?.get(0)?.subregion

            }
        })
        swipe_to_refresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            checkInternet()
        })
    }

    fun checkInternet(){
        intent.getStringExtra("country")?.let {
            if (verifyAvailableNetwork(this) == true) {
                viewModel?.getCountryDetail(it)
                countryCard.visibility = View.VISIBLE
                swipe_to_refresh.isRefreshing = true
            } else {
                swipe_to_refresh.isRefreshing = true
                countryCard.visibility = View.GONE
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
                val snackbar = Snackbar.make(
                    swipe_to_refresh,
                    "No Internet Connection",
                    Snackbar.LENGTH_INDEFINITE
                )
                snackbar.setAction("retry", View.OnClickListener {
                    Log.d(TAG, "onCreate: ITS WORKING I GUESS")
                    Toast.makeText(this, " ITS WORKING I GUESS", Toast.LENGTH_SHORT).show()
                })
                snackbar.show()
            }
        }
    }

    fun verifyAvailableNetwork(activity:AppCompatActivity):Boolean{
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

}