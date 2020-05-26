package com.test.weather_mvvm.viewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.test.weather_mvvm.model.Time
import com.test.weather_mvvm.databinding.ViewHolderBinding
import com.test.weather_mvvm.model.WeatherModel

class WeatherAdapter :RecyclerView.Adapter<WeatherAdapter.ViewHolder>{
    private lateinit var context:Context
    private var data:List<WeatherModel>
    constructor(context: Context , data:List<WeatherModel>){
        this.context = context
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lInflater = LayoutInflater.from(context)//.inflate(R.layout.view_holder, parent , false)
        val v =ViewHolderBinding.inflate(lInflater,parent,false)
        return  ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData = data[position]
        holder.bind(itemData)
        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(context, "Item $position is clicked.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    class ViewHolder :RecyclerView.ViewHolder{
        var vh:ViewHolderBinding
        constructor(vh:ViewHolderBinding):super(vh.root){
            this.vh = vh

        }

        fun bind(t:WeatherModel){
            vh.item = t
            vh.executePendingBindings()
        }
    }
}