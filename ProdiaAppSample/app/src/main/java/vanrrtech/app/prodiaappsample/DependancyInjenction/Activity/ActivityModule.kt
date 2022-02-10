package vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.Module
import dagger.Provides
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.ViewModelProducer.ViewModelProducer
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.AppScope
import vanrrtech.app.prodiaappsample.BaseClass.BaseActivity
import vanrrtech.app.prodiaappsample.UtilServices.Imageloader
import vanrrtech.app.prodiaappsample.UtilServices.LoginHandler.LoginHandlerService
import vanrrtech.app.prodiaappsample.UtilServices.location.LocationService
import vanrrtech.app.prodiaappsample.UtilServices.UnixDateConverter
import vanrrtech.app.prodiaappsample.UtilServices.alert_dialog_handler.AlertDialogHandlerService
import vanrrtech.app.prodiaappsample.UtilServices.shared_preference.SharedPreferenceService
import vanrrtech.app.prodiaappsample.UtilServices.snack_bar_handler.SnackBarHandler
import vanrrtech.app.prodiaappsample.databinding.ActivityWeatherListBinding
import vanrrtech.app.prodiaappsample.databinding.LoginActivityBinding
import vanrrtech.app.prodiaappsample.features.LoginActivity
import vanrrtech.app.prodiaappsample.features.weather_list.view.WeatherListActivity
import vanrrtech.app.prodiaappsample.features.weather_list.view.WeatherListAdapter
import vanrrtech.app.prodiaappsample.features.weather_list.view_model.WeatherListVM

@Module
class ActivityModule (val activity: AppCompatActivity, val context : Context) {

    @Provides
    fun activity() = activity

    @Provides
    fun context() : Context = context

    @Provides
    fun layoutInflater() = LayoutInflater.from(activity)

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    fun getResultLauncher(activity: AppCompatActivity) : ActivityResultLauncher<Intent> =
        activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            (activity as BaseActivity).onResult(result)
        }

    @Provides
    fun getDateConverterService() : UnixDateConverter = UnixDateConverter()

    @Provides
    fun getVMWeatherListActivity(vmProducer: ViewModelProducer, locationService: LocationService) : WeatherListVM = vmProducer
        .getViewModelWeatherList(locationService).create(WeatherListVM::class.java)

    @Provides
    fun getWeatherListVBinder(activity: AppCompatActivity): ActivityWeatherListBinding = DataBindingUtil.setContentView(
        activity as WeatherListActivity
        , (activity as WeatherListActivity).layoutId)

    @Provides
    fun getLoginVBinder(activity: AppCompatActivity): LoginActivityBinding = DataBindingUtil.setContentView(
        activity as LoginActivity
        , (activity as LoginActivity).layoutId)

    @Provides
    fun getRvAdapter(context: Context,
                     dateConverter: UnixDateConverter,
                     imageloader: Imageloader) : WeatherListAdapter = WeatherListAdapter(
        context, dateConverter, imageloader)

    /** :( dunno cause crash temporary using a lazy init **/
//    @ActivityScope
//    @Provides
//    fun getSnackBarRootView() : View = (activity as BaseActivity).getRootView()
//
//    @Provides
//    fun getSnackBarHandler(view : View) : SnackBarHandler = SnackBarHandler(view)

    @ActivityScope
    @Provides
    fun getLoginHandler(sharedPreferences: SharedPreferenceService) : LoginHandlerService = LoginHandlerService(sharedPreferences)

    @ActivityScope
    @Provides
    fun getDialogHandler() : AlertDialogHandlerService = AlertDialogHandlerService(context)

    @Provides
    @ActivityScope
    fun getImageLoader() : Imageloader = Imageloader(context)
}