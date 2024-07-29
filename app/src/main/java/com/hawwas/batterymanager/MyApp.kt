package com.hawwas.batterymanager

import android.app.*
import android.content.*
import android.os.*
import com.hawwas.batterymanager.data.database.entity.*
import com.hawwas.batterymanager.domain.repo.*
import com.hawwas.batterymanager.peresentation.*
import dagger.hilt.android.*
import kotlinx.coroutines.*
import javax.inject.*

@HiltAndroidApp
class MyApp: Application() {
    private lateinit var broadcastReceiver: BatteryManagerBroadcastReceiver
    @Inject lateinit var localRepo: LocalRepo
    private lateinit var batteryManager: BatteryManager

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate() {
        super.onCreate()
        batteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager
        broadcastReceiver = BatteryManagerBroadcastReceiver {
            val batteryInfo = BatteryUtil.createBatteryInfo(it, batteryManager)
            localRepo.upsert(BatteryData(battery = batteryInfo))
        }
        registerReceiver(broadcastReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        startPeriodicSave()
    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterReceiver(broadcastReceiver)
        job.cancel()  // Cancel the coroutine when the application terminates
    }

    private fun startPeriodicSave() {
        scope.launch {
            while (isActive) {
                delay(10000)  // Wait for 10 seconds
                saveBatteryData()
            }
        }
    }

    private fun saveBatteryData() {
        val batteryStatus: Intent? = registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        batteryStatus?.let {
            val batteryInfo = BatteryUtil.createBatteryInfo(it, batteryManager)
            localRepo.upsert(BatteryData(battery = batteryInfo))
        }
    }
}