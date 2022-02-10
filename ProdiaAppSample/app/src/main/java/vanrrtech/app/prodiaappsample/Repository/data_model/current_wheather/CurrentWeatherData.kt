package vanrrtech.app.prodiaappsample.Repository.data_model.current_wheather

import com.google.gson.annotations.SerializedName

data class CurrentWeatherData(
    @SerializedName("pressure")
    val pressure : Int,
    @SerializedName("humidity")
    val humidity : Int,
    @SerializedName("wind_speed")
    val windSpeed : Float
)