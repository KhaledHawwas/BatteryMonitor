package com.hawwas.batterymanager.data.database.dao

import androidx.lifecycle.*
import androidx.room.*
import com.hanmajid.yggr.android.os.batterymanager.model.*
import com.hawwas.batterymanager.data.database.entity.*

@Dao
interface BatteryDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAll(batteryData: BatteryData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAll(batteryData: List<BatteryData>)

    @Query("SELECT * FROM battery")
    fun getAllBatteryData(): LiveData<List<BatteryData>>

    @Delete
    fun deleteBatteryData(batteryData: BatteryData)

    @Query("SELECT * FROM battery WHERE id = :id")
    fun getBatteryDataById(id: Int): BatteryData?
}