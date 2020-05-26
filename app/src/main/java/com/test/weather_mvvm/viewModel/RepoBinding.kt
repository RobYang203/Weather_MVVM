package com.test.weather_mvvm.viewModel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.test.weather_mvvm.R
import com.test.weather_mvvm.model.Time
import com.test.weather_mvvm.model.WeatherModel
import kotlinx.android.synthetic.main.view_holder.view.*

class RepoBinding {
    companion object{

    }

}

@BindingAdapter("textFormat")
fun textFormat(v:TextView , w: WeatherModel){
    v.setText("${w.startTime}\n${w.endTime}\n${w.temperatrue}${w.unit}")
}

@BindingAdapter("bindingImage")
fun bindingImage(v:ImageView , isShowImage:Boolean){
    if(!isShowImage){
        v.visibility = View.GONE
        return;
    }
    var imageList = mutableListOf<Int>(R.drawable.pic_1 ,R.drawable.pic_2,R.drawable.pic_3,R.drawable.pic_4 ,R.drawable.pic_5)
    val rand:Int = (Math.random() * 5).toInt()

    v.visibility = View.VISIBLE
    v.setImageResource(imageList[rand])
}