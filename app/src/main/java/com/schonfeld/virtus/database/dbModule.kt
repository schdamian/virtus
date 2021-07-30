package com.schonfeld.virtus.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { Room.databaseBuilder(
        androidContext(),
        Db::class.java, "schonfeld_database")
        .allowMainThreadQueries()
        .build().hitDao()
    }
}