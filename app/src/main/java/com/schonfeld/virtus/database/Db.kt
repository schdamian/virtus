package com.schonfeld.virtus.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HitTable::class], version = 1, exportSchema = false)
abstract class Db : RoomDatabase() {
    abstract fun hitDao(): HitDao
}