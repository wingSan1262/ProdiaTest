package vanrrtech.app.prodiaappsample.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import vanrrtech.app.prodiaappsample.BaseClass.BaseActivity
import vanrrtech.app.prodiaappsample.R
import vanrrtech.app.prodiaappsample.UtilServices.LoginHandler.LoginHandlerService
import vanrrtech.app.prodiaappsample.databinding.ActivityWeatherListBinding
import vanrrtech.app.prodiaappsample.databinding.LoginActivityBinding
import vanrrtech.app.prodiaappsample.features.weather_list.view.WeatherListActivity
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    override fun getActivity() : LoginActivity { return this}
    override fun onResult(result: ActivityResult) {}
    override fun getRootView(): View {return binding.root}
    val layoutId = R.layout.login_activity

    @Inject lateinit var binding : LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)

        if(loginHandler.obtainUserCredential().userName.isNotEmpty()){
            showWeatherActivity()
        }
        initUi()

    }

    fun initUi(){

        dialogHandler.showInformationDialog("This is dummy login", "you can insert any " +
                "value to the login field as long is not empty"
        ) { /**do nothing **/ }

        binding.btnSignIn.setOnClickListener {
            if (loginHandler
                    .login(
                        binding.fieldUsername.text.toString(),
                        binding.fieldPassword.text.toString())
            ) {
                showWeatherActivity()
            } else {
                snackBarHandler.showSnackBar("you enter invalid string format")
            }
        }
    }

    fun showWeatherActivity(){
        finish()
        startActivity(Intent(this, WeatherListActivity::class.java))
    }

}