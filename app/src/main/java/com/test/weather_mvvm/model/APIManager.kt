package com.test.weather_mvvm.model

import com.test.weather_mvvm.service.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIManager {

    companion object{
        private var mInstance = APIManager()
        fun getInstance():APIManager{
            return  mInstance
        }
    }
    private lateinit var serv: WeatherService

    constructor(){
        var baseUrl = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/"
        var re = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        serv = re.create(WeatherService::class.java)
    }

    public fun getAPIService():WeatherService{
        return serv
    }

}