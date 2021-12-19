package rutledge.james.jsonplaceholderapp

import android.app.Application
import org.koin.android.ext.android.startKoin

class JSONPlaceHolderApplication : Application() {
    override fun onCreate() {
        startKoin(applicationContext, listOf(JSONPlaceholderKoinModule))
        super.onCreate()
    }
}