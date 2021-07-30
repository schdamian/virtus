package com.schonfeld.virtus.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hit_table")
data class HitTable(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "created") val created: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "active") val active: Int = 1
)