package com.schonfeld.virtus.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.schonfeld.virtus.database.HitDao
import com.schonfeld.virtus.database.HitTable
import com.schonfeld.virtus.network.NetworkProvider

class Repository(
    private val networkProvider: NetworkProvider,
    private val dao: HitDao
) : DataProvider {

    override suspend fun requestHits(): LiveData<List<HitTable>> {
        try {
            networkProvider.requestHits().getHitList()
                .filter { it.getId() != null }
                .map {
                    dao.insert(
                        HitTable(
                            id = it.getId() ?: 0,
                            title = it.getTitle(),
                            author = it.getAuthor(),
                            url = it.url() ?: "",
                            created = it.getCreatedAt(),
                            active = 1
                        )
                    )
                }
        } catch (e: Exception) {
            Log.d("requestHits", "Error: $e")
        }
        return dao.getAllRecords()
    }

    override suspend fun deleteHit(hitTable: HitTable) {
        dao.deleteHit(hitTable.id)
    }
}