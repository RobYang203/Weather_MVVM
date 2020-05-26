package com.test.weather_mvvm.model

data class Weather (
    var success:String ,
    var result:Result,
    var records:Records
)

data class Result(
     var resource_id:String,
     var fields:List<Field>
)
data class Field(
     var id:String,
     var type:String
)

data class Records(
     var datasetDescription:String,
     var location:List<Location>
)

data class Location (
     var locationName:String,
     var weatherElement:List<WeatherElement>
)
data class WeatherElement(
     var elementName:String,
     var time:List<Time>
)

data class Time(
     var startTime:String,
     var endTime:String,
     var isShowImage:Boolean,
     var parameter:TimeParameter?
)
data class TimeParameter(
     var parameterName:String,
     var parameterUnit:String
)

