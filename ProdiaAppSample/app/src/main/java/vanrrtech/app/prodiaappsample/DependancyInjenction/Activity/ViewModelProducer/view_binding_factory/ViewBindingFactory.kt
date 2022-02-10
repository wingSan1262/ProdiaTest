package vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewModelProducer.view_binding_factory

import androidx.databinding.DataBindingUtil
import vanrrtech.app.prodiaappsample.databinding.ActivityWeatherListBinding
import vanrrtech.app.prodiaappsample.databinding.LoginActivityBinding
import vanrrtech.app.prodiaappsample.features.LoginActivity
import vanrrtech.app.prodiaappsample.features.weather_list.view.WeatherListActivity

class ViewBindingFactory {

    fun getVbWeatherListActivity(activity : WeatherListActivity) : ActivityWeatherListBinding {
        return DataBindingUtil.setContentView(
            activity, activity.layoutId
        )
    }

    fun getVbLoginActivity(activity : LoginActivity) : LoginActivityBinding {
        return DataBindingUtil.setContentView(
            activity, activity.layoutId
        )
    }
}