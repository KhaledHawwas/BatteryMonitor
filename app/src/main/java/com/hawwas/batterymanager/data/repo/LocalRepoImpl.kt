package com.hawwas.batterymanager.data.repo

import androidx.lifecycle.*
import com.hanmajid.yggr.android.os.batterymanager.model.*
import com.hawwas.batterymanager.data.database.*
import com.hawwas.batterymanager.data.database.entity.*
import com.hawwas.batterymanager.domain.repo.*

class LocalRepoImpl (private val appDatabase: AppDatabase): LocalRepo {

    override fun upsert(batteryData: BatteryData) {
        appDatabase.batteryDataDao().upsertAll(batteryData)
    }

    override fun upsert(batteryData: List<BatteryData>) {
        appDatabase.batteryDataDao().upsertAll(batteryData)
    }

    override fun getAllBatteryData(): LiveData<List<BatteryData>> {
        return appDatabase.batteryDataDao().getAllBatteryData()
    }

    override fun deleteBatteryData(batteryData: BatteryData) {
        appDatabase.batteryDataDao().deleteBatteryData(batteryData)
    }

    override fun getBatteryDataById(id: Int): BatteryData? {
        return appDatabase.batteryDataDao().getBatteryDataById(id)
    }
}