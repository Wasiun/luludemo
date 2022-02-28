package com.demo.lululemon.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "garment_table")
data class GarmentEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "garment_name")val name: String
)