package com.test.weather_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.test.weather_mvvm.model.APIManager
import com.test.weather_mvvm.model.Time
import com.test.weather_mvvm.model.Weather
import com.test.weather_mvvm.model.WeatherModel
import com.test.weather_mvvm.service.IWeatherRepository
import com.test.weather_mvvm.service.WeatherRepository
import com.test.weather_mvvm.viewModel.IWeatherList
import com.test.weather_mvvm.viewModel.WeatherAdapter
import com.test.weather_mvvm.viewModel.WeatherListViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var apiMg = APIManager.getInstance()

        val api = WeatherRepository(apiMg.getAPIService(),this)
        var weatherViewModel = WeatherListViewModel(api)
        weatherViewModel.getFutureWeatherList(object : IWeatherList.ResultCallback{
            override fun onResult(list: List<WeatherModel>) {
                val lm = LinearLayoutManager(baseContext)
                lm.orientation = LinearLayoutManager.VERTICAL
                val recyclerlist = findViewById<RecyclerView>(R.id.recyclerView)
                recyclerlist.layoutManager = lm
                recyclerlist.addItemDecoration(DividerItemDecoration(baseContext , DividerItemDecoration.VERTICAL))
                recyclerlist.adapter = WeatherAdapter(baseContext , list , object :WeatherAdapter.IViewHolderClick{
                    override fun ClickCallBack(item: WeatherModel) {
                        if(item.isShowImage){
                            return
                        }

                        var g = Gson()
                        var s =  g.toJson(item)
                        var i = Intent( baseContext , SecondActivity::class.java)
                        i.putExtra(getString(R.string.secondKeyword) , s)
                        startActivityForResult(i , 2)
                    }
                })
            }
        })

    }


}
