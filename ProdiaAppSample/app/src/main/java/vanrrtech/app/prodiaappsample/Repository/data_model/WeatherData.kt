package vanrrtech.app.prodiaappsample.Repository.data_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import vanrrtech.app.prodiaappsample.Repository.data_model.current_wheather.CurrentWeatherData
import vanrrtech.app.prodiaappsample.Repository.data_model.daily_data_wheather.DailyWheatherItemData

@Entity(tableName = "weather_data_db")
data class WeatherData(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Expose
    val id:Int,

    @SerializedName("lat")
    @ColumnInfo(name = "lat")
    val lat : Float,
    @SerializedName("lon")
    @ColumnInfo(name = "lon")
    val lon : Float,
    @SerializedName("timezone")
    @ColumnInfo(name = "timezone")
    val timezone : String,
    @SerializedName("current")
    @ColumnInfo(name = "current")
    val currentData : CurrentWeatherData,
    @SerializedName("daily")
    @ColumnInfo(name = "daily")
    val daily : List<DailyWheatherItemData>
)
