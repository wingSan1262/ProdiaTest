package vanrrtech.app.prodiaappsample.Repository.remote_repository

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import vanrrtech.app.prodiaappsample.Repository.data_model.WeatherData

interface WeatherApiService {

    @GET("data/2.5/onecall")
    suspend fun getWeatherData(@Query("lat") lat : String,
                               @Query("lon") lon : String,
                               @Query("exclude") exclude : String,
                               @Query("appid") apikey : String
                               ): WeatherData

}