package vanrrtech.app.prodiaappsample.Repository.SQDb.weather_item

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vanrrtech.app.prodiaappsample.Repository.data_model.daily_data_wheather.WeatherItemData
import java.lang.reflect.Type

@ProvidedTypeConverter
class WeatherItemDataTypeConverter {

    @TypeConverter
    fun fromWeatherItemData(weatherItem: List<WeatherItemData>?): String? {
        if (weatherItem == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<WeatherItemData>?>() {}.type
        return gson.toJson(weatherItem, type)
    }

    @TypeConverter
    fun toWeatherItemData(countryLangString: String?): List<WeatherItemData>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<WeatherItemData>?>() {}.type
        return gson.fromJson<List<WeatherItemData>>(countryLangString, type)
    }
}