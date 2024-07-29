package com.hawwas.batterymanager.data.database.entity

import androidx.room.*
import com.hanmajid.yggr.android.os.batterymanager.model.*

@Entity(tableName = "battery")
data class BatteryData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Embedded

    val battery: BatteryInfo,
)