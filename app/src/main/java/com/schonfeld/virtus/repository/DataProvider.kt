package com.schonfeld.virtus.repository

import androidx.lifecycle.LiveData
import com.schonfeld.virtus.database.HitTable

interface DataProvider {
    suspend fun requestHits(): LiveData<List<HitTable>>
    suspend fun deleteHit(hitTable: HitTable)
}