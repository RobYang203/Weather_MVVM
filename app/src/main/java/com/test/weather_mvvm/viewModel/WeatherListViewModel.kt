package com.test.weather_mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.weather_mvvm.model.Time
import com.test.weather_mvvm.model.Weather
import com.test.weather_mvvm.model.WeatherModel
import com.test.weather_mvvm.service.IWeatherRepository
import com.test.weather_mvvm.service.WeatherRepository

interface IWeatherList {
    fun getFutureWeatherList(resultCallback: IWeatherList.ResultCallback)

    interface ResultCallback {

        fun onResult(list: List<WeatherModel>)
    }


}
class WeatherListViewModel(private val weatherRepo:WeatherRepository):IWeatherList {
    var list:List<WeatherModel> = mutableListOf()

    override fun getFutureWeatherList(resultCallback: IWeatherList.ResultCallback){
        weatherRepo.getWeatherList(object :IWeatherRepository.LoadCallback{
            override fun onResult(response: Weather) {
                val records = response.records
                val timeList = records.location[0].weatherElement[0].time
                list = setTimeTotalList(timeList)
                resultCallback.onResult(list)
            }
        })
    }


    fun setTimeTotalList(list:List<Time>):MutableList<WeatherModel>{
        var mList = mutableListOf<WeatherModel>()

        var size = list.size * 2
        var i = 1

        while (i <= size){
            var tmp = WeatherModel("","","","",false)
            if(i%2 == 0){
                tmp.isShowImage = true
            }
            else{
                val listIndex = (i/2)
                tmp.startTime =  list[listIndex].startTime
                tmp.endTime =  list[listIndex].endTime
                tmp.temperatrue =  list[listIndex].parameter?.parameterName as String
                tmp.unit =  list[listIndex].parameter?.parameterUnit as String
                tmp.isShowImage = false
            }

            mList.add(tmp)
            i++
        }

        return  mList
    }
}