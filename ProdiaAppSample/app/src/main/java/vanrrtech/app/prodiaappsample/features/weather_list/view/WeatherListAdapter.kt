package vanrrtech.app.prodiaappsample.features.weather_list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vanrrtech.app.prodiaappsample.R
import vanrrtech.app.prodiaappsample.Repository.RepositoryInteractor.GetMyWeatherData.GetMyWeatherInteractor
import vanrrtech.app.prodiaappsample.Repository.data_model.daily_data_wheather.WeatherItemData
import vanrrtech.app.prodiaappsample.Repository.data_model.daily_data_wheather.DailyWheatherItemData
import vanrrtech.app.prodiaappsample.UtilServices.Imageloader
import vanrrtech.app.prodiaappsample.UtilServices.UnixDateConverter
import vanrrtech.app.prodiaappsample.databinding.WheatherItemRvBinding

class WeatherListAdapter(val context: Context, val dateConverter: UnixDateConverter, val imageloader: Imageloader) : RecyclerView.Adapter<WeatherListAdapter.WeatherListAdapterHolder>() {

    lateinit var mContext : Context
    var mList = ArrayList<DailyWheatherItemData>()

    init{
        mContext = context
    }

    fun reset (){
        mList.clear()
        notifyDataSetChanged()
    }

    fun onAddItem (item : DailyWheatherItemData){
        mList.add(0, item)
        notifyItemInserted(0)
    }

    class WeatherListAdapterHolder (_binding : WheatherItemRvBinding) : RecyclerView.ViewHolder(_binding.root) {

        var binding : WheatherItemRvBinding? = null
        lateinit var item : DailyWheatherItemData
        init {
            binding = _binding
        }
        fun setUI(context: Context,
                  weatherItem: DailyWheatherItemData,
                  dateConverter: UnixDateConverter,
                  imageloader: Imageloader){

            val weather : WeatherItemData = weatherItem.weatherList[0]

            binding?.tvDate?.text = dateConverter.convertUnixTimeToDateString(weatherItem.dt)
            binding?.tvDetailDesc?.text = weather.detailDesc
            binding?.tvMainDesc?.text = weather.mainDesc
            binding?.tvWeatherId?.text = "weather id : ${weather.id}"
            imageloader.loadImage(GetMyWeatherInteractor.IMAGE_LINK
                    + "${weather.iconCode}@2x.png"
                , binding?.wheatherImg!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListAdapterHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mBinding : WheatherItemRvBinding = DataBindingUtil.inflate(layoutInflater, R.layout.wheather_item_rv, parent, false)
        return  WeatherListAdapterHolder(mBinding)
    }

    override fun onBindViewHolder(holder: WeatherListAdapterHolder, position: Int) {
        holder.setUI(mContext, mList.get(position), dateConverter, imageloader)
    }

    override fun getItemCount(): Int {
        return mList.size
    }


}
