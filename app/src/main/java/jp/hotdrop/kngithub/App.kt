package jp.hotdrop.kngithub

import android.app.Application
import jp.hotdrop.kngithub.module.appModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()

        startKoin {
            logger(level = Level.DEBUG)
            modules(appModule)
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}