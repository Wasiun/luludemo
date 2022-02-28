package com.demo.lululemon.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GarmentDao {
    @Query("SELECT garment_name from GARMENT_TABLE ORDER BY garment_name ASC")
    suspend fun getGarmentsByName(): List<String>

    @Query("SELECT garment_name from GARMENT_TABLE ORDER BY id ASC")
    suspend fun getGarmentsByTime(): List<String>

    @Insert
    suspend fun insertGarment(garment: GarmentEntity)
}