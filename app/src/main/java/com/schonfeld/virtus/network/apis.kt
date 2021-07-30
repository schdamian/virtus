package com.schonfeld.virtus.network

import retrofit2.Retrofit

internal fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)