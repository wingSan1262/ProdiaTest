package vanrrtech.app.prodiaappsample.Repository.remote_repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vanrrtech.app.prodiaappsample.Repository.data_model.WeatherData

class WeatherApiRetrofitClient {

    companion object{
        val BASE_URL = "https://api.openweathermap.org/"
    }

    private val logging by lazy { HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY) }
    private  val mClient by lazy {
        OkHttpClient.Builder().addInterceptor(logging)
    }
    private  val userService : WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mClient.build())
            .build()
            .create(WeatherApiService::class.java)
    }

    suspend fun getWeatherQueryApi(lat : String, lon : String, exclued : String, apikey : String): WeatherData {
        return userService.getWeatherData(lat, lon, exclued, apikey)
    }




}