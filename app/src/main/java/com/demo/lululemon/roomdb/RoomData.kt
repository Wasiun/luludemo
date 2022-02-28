package com.demo.lululemon.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(GarmentEntity::class), version = 1)
abstract class RoomData : RoomDatabase() {
    abstract fun getMyEntityDao(): GarmentDao

    companion object {
        @Volatile
        private var INSTANCE: RoomData? = null
        fun getInstance(context: Context): RoomData {
            if(INSTANCE != null) return INSTANCE as RoomData
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(context, RoomData::class.java, "AWESOME_TABLE").build()
                return INSTANCE as RoomData
            }
        }
    }
}