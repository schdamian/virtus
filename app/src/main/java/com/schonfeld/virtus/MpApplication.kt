package com.schonfeld.virtus

import android.app.Application
import com.schonfeld.virtus.network.networkModule
import com.schonfeld.virtus.repository.repositoryModule
import com.schonfeld.virtus.database.dbModule
import com.schonfeld.virtus.ui.viewModels.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MpApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MpApplication)
            modules(
                listOf(
                    dbModule,
                    repositoryModule,
                    networkModule,
                    mainModule
                )
            )
        }
    }
}