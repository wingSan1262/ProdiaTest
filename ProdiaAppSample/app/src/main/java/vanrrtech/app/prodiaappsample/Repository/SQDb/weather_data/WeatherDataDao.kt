package vanrrtech.app.prodiaappsample.Repository.SQDb.weather_data

import androidx.room.*
import vanrrtech.app.prodiaappsample.Repository.data_model.WeatherData
import vanrrtech.app.prodiaappsample.Repository.data_model.daily_data_wheather.DailyWheatherItemData

@Dao
interface WeatherDataDao {

    @Query("Select * from weather_data_db")
    fun loadAllUser() : List<WeatherData?>?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers (movie : WeatherData)
    @Update
    fun updateUser (movie : WeatherData)
    @Delete
    fun deleteUser (movie : WeatherData)

    @Query("DELETE FROM weather_data_db")
    fun nukeTable()
}