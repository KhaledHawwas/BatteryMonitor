package com.hanmajid.yggr.android.os.batterymanager.model

import androidx.room.*

@Entity(tableName = "battery_table")
data class BatteryInfo(
    @PrimaryKey
    val timestamp: Long,
    val iconSmall: Int,
    val action: String,
    val health: Int,
    val status: Int,
    val voltage: Int,
    val temperature: Int,
    val technology: String?,
    val level: Int,
    val scale: Int,
    val present: Boolean,
    val batteryLow: Boolean?,
    val plugged: Int,
    val propertyCapacity: Int,
    val propertyChargeCounter: Int,
    val propertyCurrentAverage: Int,
    val propertyCurrentNow: Int,
    val propertyEnergyCounter: Long,
    // Min. API Level 26
    val propertyStatus: Int?,

    val isCharging: Boolean,

    // Min. API Level 29
    val chargeTimeRemaining: Long?,
)