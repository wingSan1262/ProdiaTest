package vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.ViewModelProducer

import android.app.Application
import vanrrtech.app.prodiaappsample.features.weather_list.view_model.WeatherListVMFactory
import vanrrtech.app.prodiaappsample.Repository.RepositoryInteractor.GetMyWeatherData.GetMyWeatherInteractor
import vanrrtech.app.prodiaappsample.UtilServices.location.LocationService

class ViewModelProducer (private val mRepo : GetMyWeatherInteractor, private val application: Application ){

    fun getViewModelWeatherList(locationService: LocationService): WeatherListVMFactory {
        return WeatherListVMFactory(application, mRepo, locationService)
    }

}