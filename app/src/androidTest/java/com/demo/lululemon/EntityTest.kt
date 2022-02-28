package com.demo.lululemon

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.demo.lululemon.roomdb.GarmentDao
import com.demo.lululemon.roomdb.GarmentEntity
import com.demo.lululemon.roomdb.RoomData
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class EntityTest {
    private lateinit var garmentDao: GarmentDao
    private lateinit var db: RoomData

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, RoomData::class.java).build()
        garmentDao = db.getMyEntityDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeGarmentAndReadInList() {
        val garment = GarmentEntity(0,"a")
        runBlocking{
            garmentDao.insertGarment(garment)
            val byName = garmentDao.getGarmentsByName()
            assertThat(byName.size, `is`(1))
        }
    }

    @Test
    @Throws(Exception::class)
    fun writeGarmentListAndReadInListMatchSortedName() {

        runBlocking{
            var garment = GarmentEntity(0,"b")
            garmentDao.insertGarment(garment)
            garment = GarmentEntity(0,"a")
            garmentDao.insertGarment(garment)
            garment = GarmentEntity(0,"c")
            garmentDao.insertGarment(garment)

            val byName = garmentDao.getGarmentsByName()
            assertTrue(byName.equals(listOf("a","b","c")))
        }
    }

    @Test
    @Throws(Exception::class)
    fun writeGarmentListAndReadInListMatchSortedID() {

        runBlocking{
            var garment = GarmentEntity(0,"b")
            garmentDao.insertGarment(garment)
            garment = GarmentEntity(0,"a")
            garmentDao.insertGarment(garment)
            garment = GarmentEntity(0,"c")
            garmentDao.insertGarment(garment)

            val byName = garmentDao.getGarmentsByTime()
            assertTrue(byName.equals(listOf("b","a","c")))
        }
    }
}
