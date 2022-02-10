package vanrrtech.app.prodiaappsample.features.weather_list.view_model

import android.app.Application
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import vanrrtech.app.prodiaappsample.Repository.RepositoryInteractor.GetMyWeatherData.GetMyWeatherInteractor
import vanrrtech.app.prodiaappsample.UtilServices.location.LocationService


class WeatherListVMFactory(val mApplication: Application,
                           val repo : GetMyWeatherInteractor,
                           val locationService : LocationService
) :
    ViewModelProvider.Factory {
    private val mRepo by lazy {
        repo
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherListVM(mRepo, mApplication, locationService) as T
    }

}