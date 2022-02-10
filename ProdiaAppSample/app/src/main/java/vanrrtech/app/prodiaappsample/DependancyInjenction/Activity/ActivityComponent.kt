package vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity

import dagger.Subcomponent
import vanrrtech.app.prodiaappsample.features.LoginActivity
import vanrrtech.app.prodiaappsample.features.weather_list.view.WeatherListActivity

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(context: WeatherListActivity)
    fun inject(context: LoginActivity)

}