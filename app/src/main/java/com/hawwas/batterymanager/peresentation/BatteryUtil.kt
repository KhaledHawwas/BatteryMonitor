package com.hawwas.batterymanager.peresentation

import android.content.*
import android.os.*
import com.hanmajid.yggr.android.os.batterymanager.model.*
import java.util.*

class BatteryUtil {

    companion object {

        fun getBatteryHealthText(batteryHealth: Int): String {
            return when (batteryHealth) {
                BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
                BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Unspecified Failure"
                BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over Voltage"
                BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
                BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Overheat"
                BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
                BatteryManager.BATTERY_HEALTH_UNKNOWN -> "Unknown"
                else -> "Unknown"
            }
        }

        fun getBatteryStatusText(batteryStatus: Int?): String {
            return when (batteryStatus) {
                BatteryManager.BATTERY_STATUS_FULL -> "Full"
                BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not Charging"
                BatteryManager.BATTERY_STATUS_DISCHARGING -> "Discharging"
                BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
                BatteryManager.BATTERY_STATUS_UNKNOWN -> "Unknown"
                else -> "Unknown"
            }
        }

        fun getBatteryPluggedText(batteryPlugged: Int): String {
            return when (batteryPlugged) {
                BatteryManager.BATTERY_PLUGGED_AC -> "AC"
                BatteryManager.BATTERY_PLUGGED_USB -> "USB"
                BatteryManager.BATTERY_PLUGGED_WIRELESS -> "Wireless"
                else -> "Unknown"
            }
        }
        /**
         * Convert Kelvin to Celsius
         */
        fun Int.toCelsius(): Int {
        return this - 273
        }
        fun createBatteryInfo(intent : Intent, batteryManager: BatteryManager):BatteryInfo{
            val propertyCapacity =
                batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
            val propertyChargeCounter =
                batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER)
            val propertyEnergyCounter =
                batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER)
            val propertyCurrentAverage =
                batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE)
            val propertyCurrentNow =
                batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)

            var propertyStatus: Int = batteryManager.getIntProperty(
                BatteryManager.BATTERY_PROPERTY_STATUS
            )

            var chargeTimeRemaining: Long? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                chargeTimeRemaining = batteryManager.computeChargeTimeRemaining()
                chargeTimeRemaining = if (chargeTimeRemaining==-1L) null else chargeTimeRemaining
            }

            val iconSmall = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, -99)
            val health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -99)
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -99)
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -99)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -99)
            val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -99)
            val present = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false)
            val technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)
            val batteryLow = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                intent.getBooleanExtra(BatteryManager.EXTRA_BATTERY_LOW, false)
            } else {
                null
            }
            val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -99)
            val temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -99)

            return BatteryInfo(
                timestamp = Date().time,
                iconSmall = iconSmall,
                action = intent.action.toString(),
                health = health,
                status = status,
                level = level,
                scale = scale,
                plugged = plugged,
                present = present,
                technology = technology,
                batteryLow = batteryLow,
                voltage = voltage,
                temperature = temperature,
                propertyCapacity = propertyCapacity,
                propertyChargeCounter = propertyChargeCounter,
                propertyEnergyCounter = propertyEnergyCounter,
                propertyCurrentAverage = propertyCurrentAverage,
                propertyCurrentNow = propertyCurrentNow,
                propertyStatus = propertyStatus,
                isCharging =  batteryManager.isCharging,
                chargeTimeRemaining = chargeTimeRemaining,
            )
        }
    }
}