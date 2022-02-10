package vanrrtech.app.prodiaappsample.Repository.data_model.daily_data_wheather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "weather_item_db")
data class DailyWheatherItemData(
    @PrimaryKey
    @SerializedName("dt")
    @ColumnInfo(name = "dt")
    val dt : Double,
    @SerializedName("weather")
    @ColumnInfo(name = "weather")
    val weatherList : List<WeatherItemData>
)
