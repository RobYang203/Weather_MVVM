package com.test.weather_mvvm.service

import com.test.weather_mvvm.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("F-C0032-001")
    fun getWeatherList(@Query("Authorization")Authorization:String ,@Query("locationName")locationName:String,@Query("elementName")elementName:String ): Call<Weather>
}