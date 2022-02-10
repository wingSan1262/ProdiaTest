package vanrrtech.app.prodiaappsample.BaseClass

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.ActivityComponent
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.ActivityModule
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.AppComponent
import vanrrtech.app.prodiaappsample.Application.MyApplication
import vanrrtech.app.prodiaappsample.UtilServices.Imageloader
import vanrrtech.app.prodiaappsample.UtilServices.LoginHandler.LoginHandlerService
import vanrrtech.app.prodiaappsample.UtilServices.alert_dialog_handler.AlertDialogHandlerService
import vanrrtech.app.prodiaappsample.UtilServices.location.ActivityLocationPermissionRequest
import vanrrtech.app.prodiaappsample.UtilServices.snack_bar_handler.SnackBarHandler
import vanrrtech.app.prodiaappsample.databinding.ActivityWeatherListBinding
import vanrrtech.app.prodiaappsample.features.weather_list.view_model.WeatherListVM
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getActivity() : AppCompatActivity
    abstract fun onResult(result : ActivityResult)
    abstract fun getRootView() : View

    @Inject lateinit var viewModel : WeatherListVM
    @Inject lateinit var resultLauncher : ActivityResultLauncher<Intent>
    @Inject lateinit var dialogHandler : AlertDialogHandlerService
    @Inject lateinit var loginHandler : LoginHandlerService
    @Inject lateinit var imageLoader: Imageloader

    val snackBarHandler by lazy { SnackBarHandler(getRootView())}

    val appComponent : AppComponent by lazy {
        (application as MyApplication).myAppComponent
    }

    public val activityComponent : ActivityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this, this))
    }

    fun requestPermission(){
        ActivityLocationPermissionRequest.requestLocation(this)
    }

    fun openAppSetting(){
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri: Uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }

}