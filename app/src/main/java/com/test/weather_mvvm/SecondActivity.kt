package com.test.weather_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.test.weather_mvvm.databinding.ActivitySecondBinding
import com.test.weather_mvvm.model.APIManager
import com.test.weather_mvvm.model.Time
import com.test.weather_mvvm.model.Weather
import com.test.weather_mvvm.model.WeatherModel
import com.test.weather_mvvm.service.IWeatherRepository
import com.test.weather_mvvm.service.WeatherRepository
import com.test.weather_mvvm.viewModel.IWeatherList
import com.test.weather_mvvm.viewModel.WeatherAdapter
import com.test.weather_mvvm.viewModel.WeatherListViewModel

class SecondActivity : AppCompatActivity() {
    private var startAgain = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var dataBinding = DataBindingUtil.setContentView<ActivitySecondBinding>(this , R.layout.activity_second)
        var sResult = intent.getStringExtra(getString(R.string.secondKeyword))
        if(sResult.isEmpty()){
            return
        }

        var g = Gson()
        var item = g.fromJson(sResult,WeatherModel::class.java)

        dataBinding.weatherItem = item
        dataBinding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        if(startAgain){
            Toast.makeText(baseContext,"welcome back!!",Toast.LENGTH_LONG).show()
            startAgain = false
        }
    }

    override fun onPause() {
        super.onPause()
        startAgain = true
    }

}
