package com.schonfeld.virtus.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HitDao {

    @Query("SELECT * FROM hit_table WHERE active = 1 ORDER BY created")
    fun getAllRecords() : LiveData<List<HitTable>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(hit: HitTable)

    @Query("UPDATE hit_table SET active = 0 where id = :hitId")
    fun deleteHit(hitId: Int)
}