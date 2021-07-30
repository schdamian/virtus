package com.schonfeld.virtus.network

import com.schonfeld.virtus.models.Hits

class Service (private val api: Api) : NetworkProvider {

    override suspend fun requestHits(): Hits {
        return api.requestHit("android")
    }
}