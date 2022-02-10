package vanrrtech.app.prodiaappsample.features.weather_list.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import vanrrtech.app.prodiaappsample.BaseClass.BaseActivity
import vanrrtech.app.prodiaappsample.R
import vanrrtech.app.prodiaappsample.UtilServices.location.LocationService
import vanrrtech.app.prodiaappsample.databinding.ActivityWeatherListBinding
import vanrrtech.app.prodiaappsample.features.LoginActivity
import javax.inject.Inject

class WeatherListActivity : BaseActivity() {


    @Inject lateinit var binding : ActivityWeatherListBinding
    @Inject lateinit var weatherAdapter : WeatherListAdapter
    @Inject lateinit var mLocationService: LocationService
    val layoutId = R.layout.activity_weather_list

    override fun onCreate(savedInstanceState: Bundle?) {

        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        initUi()
        initObserver()
        if(!mLocationService.isPermissionGranted()){
            requestPermission()
        }

    }

    override fun getActivity(): WeatherListActivity {return this}

    override fun onResult(result: ActivityResult) {
        // TODO still handle permission only
        if (result.resultCode == Activity.RESULT_OK) {
            finish()
            startActivity(Intent(this, WeatherListActivity::class.java))
        } else {
            binding.swipeRefresh.isRefreshing = false
            binding.tvNoDataError.text = "You deny the permission, please restart app / approve it manually by click here so we can fetch the weather data"
            binding.tvNoDataError.setOnClickListener {
                openAppSetting()
            }
        }
    }

    override fun getRootView(): View { return binding.root}

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }

    fun initUi(){

        snackBarHandler.showSnackBar("Obtaining your region please wait a moment, it can take for a while")

        binding.swipeRefresh.isRefreshing = true
        binding.rvWeatherList.apply {
            layoutManager = LinearLayoutManager(this@WeatherListActivity)
            adapter = weatherAdapter
        }
        
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchData()
            weatherAdapter.reset()
        }

        val userData = loginHandler.obtainUserCredential()
        binding.tvUserName.text = userData.userName
        imageLoader.loadImage(userData.userImg, binding.imgUser)
        binding.tvLogout.setOnClickListener {
            dialogHandler.showQuestionDialog(
                "Do you want to log out",
                "All your credential will be all deleted",
                { loggingOut() }, // create activity / fragment navigation service :(
                { /** donothing **/ }
            )
        }
    }

    fun loggingOut() {
        loginHandler.logout()
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun initObserver(){
        viewModel.fetchData()
        viewModel.currentData.observe(this, Observer {
            binding.error.visibility = View.GONE
            binding.swipeRefresh.isRefreshing = false
            binding.tvLatitude.text = it?.lat.toString()
            binding.tvLongitude.text = it?.lon.toString()
            binding.tvTimezone.text = it?.timezone.toString()
            binding.tvPressure.text = it?.currentData?.pressure.toString()
            binding.tvHumidity.text = it?.currentData?.humidity.toString()
            binding.windSpeed.text = it?.currentData?.windSpeed.toString()
        })

        viewModel.dailyWeather.observe(this, Observer {
            if(it == null){return@Observer}
            weatherAdapter.onAddItem(it)
            binding.rvWeatherList.smoothScrollToPosition(0)
        })

        viewModel.isError.observe(this, Observer {

            if (it == true){
                binding.swipeRefresh.isRefreshing = false
                snackBarHandler.showSnackBar("Oops an error occurede, please check your internet connection")
            }
        })
    }

}