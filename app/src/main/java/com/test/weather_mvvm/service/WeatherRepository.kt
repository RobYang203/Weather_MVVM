package com.test.weather_mvvm.service

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.test.weather_mvvm.model.Weather
import com.test.weather_mvvm.R
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
interface IWeatherRepository {
    fun getWeatherList(loadCallback: IWeatherRepository.LoadCallback)

    interface LoadCallback {

        fun onResult(response: Weather)
    }


}
class WeatherRepository:IWeatherRepository {
    lateinit var APIService:WeatherService
    lateinit var context: Context
    constructor(APIService:WeatherService , context: Context){
        this.APIService = APIService
        this.context = context
    }


    override fun getWeatherList(loadCallback: IWeatherRepository.LoadCallback){
        var data = MutableLiveData<Weather>()
        var apiKey = context.getString(R.string.authorization_Key)
        APIService.getWeatherList(apiKey , "臺北市" , "MinT").enqueue(object :retrofit2.Callback<Weather>{
            override fun onResponse(
                call: Call<Weather>?,
                response: Response<Weather>?
            ) {
                val g = Gson()
                loadCallback.onResult(response?.body() as Weather)
                Log.d("API Response" , g.toJson(response))
            }

            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                Log.e("API Response Error" ,t.toString())
            }
        })

    }
}