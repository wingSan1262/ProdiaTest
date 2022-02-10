package vanrrtech.app.kompasgithubapp.app.DependancyInjenction

import android.app.Application
import dagger.Module
import dagger.Provides
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.ViewModelProducer.ViewModelProducer
import vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewModelProducer.view_binding_factory.ViewBindingFactory
import vanrrtech.app.prodiaappsample.Repository.RepositoryInteractor.GetMyWeatherData.GetMyWeatherInteractor
import vanrrtech.app.prodiaappsample.Repository.SQDb.weather_data.WeatherDataDao
import vanrrtech.app.prodiaappsample.Repository.SQDb.weather_data.WeatherDataDb
import vanrrtech.app.prodiaappsample.Repository.remote_repository.WeatherApiRetrofitClient
import vanrrtech.app.prodiaappsample.UtilServices.Imageloader
import vanrrtech.app.prodiaappsample.UtilServices.location.LocationService
import vanrrtech.app.prodiaappsample.UtilServices.shared_preference.SharedPreferenceService

@Module
class AppModule(val application: Application) {

    @Provides
    @AppScope
    fun getClient(): WeatherApiRetrofitClient {
        return WeatherApiRetrofitClient()
    }

    @Provides
    @AppScope
    fun getWeatherApi(myClient: WeatherApiRetrofitClient, weatherDataDao: WeatherDataDao): GetMyWeatherInteractor {
        return GetMyWeatherInteractor(myClient, weatherDataDao)
    }

    @Provides
    @AppScope
    fun getWeatherDataDB(application: Application): WeatherDataDb {
        return WeatherDataDb.getInstance(application.applicationContext)
    }

    @Provides
    @AppScope
    fun getWeatherDataDbDao(db : WeatherDataDb): WeatherDataDao {
        return db.weatherDataDao()
    }

    @Provides
    fun application() = application

    @Provides
    @AppScope
    fun getViewModelFactoryProducer(mRepository: GetMyWeatherInteractor,
                                    application : Application) : ViewModelProducer = ViewModelProducer(mRepository, application)

    @Provides
    @AppScope
    fun getLocationServiceProvider(application: Application) : LocationService = LocationService(application)


    @Provides
    @AppScope
    fun getSharedPreferenceService(): SharedPreferenceService = SharedPreferenceService(application)


    @Provides
    @AppScope
    fun getVbFactory() : ViewBindingFactory = ViewBindingFactory()

}