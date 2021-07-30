package com.schonfeld.virtus.repository

import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get(), get()) as DataProvider }
}