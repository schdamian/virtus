package com.schonfeld.virtus.network

import com.schonfeld.virtus.models.Hits

interface NetworkProvider {
    suspend fun requestHits(): Hits
}