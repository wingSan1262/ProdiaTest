package vanrrtech.app.kompasgithubapp.app.DependancyInjenction

import dagger.Component
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.ActivityComponent
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.ActivityModule

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponent (activityModule: ActivityModule) : ActivityComponent

}