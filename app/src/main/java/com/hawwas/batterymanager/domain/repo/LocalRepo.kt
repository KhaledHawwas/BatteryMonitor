package com.hawwas.batterymanager.domain.repo

import androidx.lifecycle.*
import com.hanmajid.yggr.android.os.batterymanager.model.*
import com.hawwas.batterymanager.data.database.entity.*

interface LocalRepo {

    fun upsert(batteryData: BatteryData)

    fun upsert(batteryData: List<BatteryData>)

    fun getAllBatteryData(): LiveData<List<BatteryData>>

    fun deleteBatteryData(batteryData: BatteryData)

    fun getBatteryDataById(id: Int): BatteryData?
}