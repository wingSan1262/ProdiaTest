package vanrrtech.app.prodiaappsample.Repository.SQDb.weather_item

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vanrrtech.app.prodiaappsample.Repository.data_model.daily_data_wheather.DailyWheatherItemData

@Database(entities = [DailyWheatherItemData::class], exportSchema = false, version = 1)
@TypeConverters(WeatherItemDataTypeConverter::class)
public abstract class DailyWeatherDataDb : RoomDatabase() {
    companion object {
        val DB_NAME: String = "weather_daily_db"
        private var instance: DailyWeatherDataDb? = null

        fun getInstance(context: Context): DailyWeatherDataDb? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DailyWeatherDataDb::class.java,
                    DB_NAME
                )
                    .addTypeConverter(WeatherItemDataTypeConverter())
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }

    public abstract fun weatherItemDao(): WeatherItemDao
}