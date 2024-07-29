package com.hawwas.batterymanager.data.database

import android.content.*
import androidx.room.*
import androidx.room.Room
import com.hanmajid.yggr.android.os.batterymanager.model.*
import com.hawwas.batterymanager.data.database.dao.*
import com.hawwas.batterymanager.data.database.entity.*

const val databaseName = "Database.db"
const val dbVersion = 1
@Database(
    entities = [ BatteryData::class],
    version = dbVersion,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun batteryDataDao(): BatteryDataDao
}