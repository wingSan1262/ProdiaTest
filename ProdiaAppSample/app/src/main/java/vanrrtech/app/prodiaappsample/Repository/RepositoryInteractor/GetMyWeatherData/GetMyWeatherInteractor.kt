package vanrrtech.app.prodiaappsample.Repository.RepositoryInteractor.GetMyWeatherData

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import vanrrtech.app.prodiaappsample.Repository.SQDb.weather_data.WeatherDataDao
import vanrrtech.app.prodiaappsample.Repository.data_model.WeatherData
import vanrrtech.app.prodiaappsample.Repository.remote_repository.WeatherApiRetrofitClient

class GetMyWeatherInteractor (private val myApi : WeatherApiRetrofitClient, private val dataDao: WeatherDataDao) {

    data class Param (val lat : String,
                      val lon : String,
                      val exclude : String,
                      val apikey : String)

    companion object{
        val API_KEY = "cb0a5e32ec668af3f4afa3feb6da84f1"
        val TEMP_EXCLUDE_PARAM = "hourly,minutely"
        val IMAGE_LINK = "https://openweathermap.org/img/wn/"
    }

    suspend fun getWeatherDataSync(
        param: Param,
        showError: (boolean:Boolean) -> Unit
    ): WeatherData? {
        var mWeatherData : WeatherData? = null
        try {
            mWeatherData =  myApi.getWeatherQueryApi(param.lat, param.lon, param.exclude, param.apikey)

        } catch (e : Throwable){
            Log.e("Internet down", "error")
            mWeatherData = null
        }

        if(mWeatherData == null){
            mWeatherData = dataDao.loadAllUser()?.get(0)
            mWeatherData?.let{
                runBlocking {
                    launch(Dispatchers.Main) {
                        showError(true)
                    }
                }
            }
        }


        mWeatherData?.let {
            dataDao.nukeTable()
            dataDao.insertUsers(it)
        }
        return mWeatherData
    }




}