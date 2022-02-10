package vanrrtech.app.prodiaappsample.features.weather_list.view_model


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import vanrrtech.app.prodiaappsample.Repository.RepositoryInteractor.GetMyWeatherData.GetMyWeatherInteractor
import vanrrtech.app.prodiaappsample.Repository.data_model.WeatherData
import vanrrtech.app.prodiaappsample.Repository.data_model.daily_data_wheather.DailyWheatherItemData
import vanrrtech.app.prodiaappsample.UtilServices.location.LocationService

class WeatherListVM(val myWeatherInteractor: GetMyWeatherInteractor, application: Application, val locationService: LocationService) : AndroidViewModel(
    application
) {

    private val _currentData = MutableLiveData<WeatherData?>()
    val currentData : LiveData<WeatherData?> = _currentData

    private val _dailyWeather = MutableLiveData<DailyWheatherItemData?>()
    val dailyWeather : LiveData<DailyWheatherItemData?> = _dailyWeather

    private val _isError = MutableLiveData<Boolean?>()
    val isError : LiveData<Boolean?> = _isError

    val viewCoroutineScope : CoroutineScope = CoroutineScope(Dispatchers.IO)


    fun fetchData (){
        showError(false) // TODO dunno i dont like this for resetting
        viewCoroutineScope.launch {

            if(!locationService.isPermissionGranted()){
                launch(Dispatchers.Main){
                    showError(true)
                }
                return@launch
            }

            val coordinate = locationService.getLatAndLon()
            if (coordinate == null){
                launch(Dispatchers.Main){
                    showError(true)
                }
                return@launch
            }
            val param = GetMyWeatherInteractor.Param(
                coordinate.lat.toString(),
                coordinate.lon.toString(),
                GetMyWeatherInteractor.TEMP_EXCLUDE_PARAM,
                GetMyWeatherInteractor.API_KEY
            )

            val mData = myWeatherInteractor.getWeatherDataSync(param, ::showError)

            launch(Dispatchers.Main){
                showError(mData == null)
            }
            launch(Dispatchers.Main){
                mData?.daily?.let { updateDailyWeatherList(it) }
            }
            launch(Dispatchers.Main){
                mData?.let { updateCurrentWeather(it) }
            }

        }
    }

    fun showError(boolean: Boolean){
        _isError.value = boolean
    }

    suspend fun updateDailyWeatherList(dailyList : List<DailyWheatherItemData>){
        val reversedList = dailyList.reversed()
        reversedList.forEach { item ->
            _dailyWeather.value = item
            delay(500)
        }
    }

    fun updateCurrentWeather(currentData : WeatherData){
        _currentData.value = currentData
    }

    fun destroy(){

    }



}