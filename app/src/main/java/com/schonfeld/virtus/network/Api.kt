package com.schonfeld.virtus.network

import com.schonfeld.virtus.models.Hits
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("v1/search_by_date")
    suspend fun requestHit(
        @Query("query") topic: String
    ): Hits
}