package com.hawwas.batterymanager.peresentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hawwas.batterymanager.*
import com.hawwas.batterymanager.databinding.ActivityMainBinding
import com.hawwas.batterymanager.domain.repo.*
import com.hawwas.batterymanager.peresentation.BatteryUtil.Companion.toCelsius
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    @Inject
   lateinit var localRepo: LocalRepo
   private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        localRepo.getAllBatteryData().observe(this) {

            it.lastOrNull()?.let { batteryE ->
               val  batteryData=batteryE.battery
                binding.batteryDataLyt.apply {
                    timestampTv.text = Date(batteryData.timestamp).toString()
                    healthTv.text = BatteryUtil.getBatteryHealthText(batteryData.health)

                    levelAndScaleTv.text = "${batteryData.level} / ${batteryData.scale}"
                    pluggedTv.text = BatteryUtil.getBatteryPluggedText(batteryData.plugged)
                    presentTv.text = batteryData.present.toString()
                    technologyTv.text = batteryData.technology ?: "Unknown"
                    batteryLowTv.text = batteryData.batteryLow?.toString() ?: "Unknown"
                    voltageTv.text = batteryData.voltage.toString()
                    temperatureTv.text = batteryData.temperature.toCelsius().toString()

                    capacityTv.text = batteryData.propertyCapacity.toString()
                    chargeCounterTv.text = "${batteryData.propertyChargeCounter} µAh"
                    energyCounterTv.text = "${batteryData.propertyEnergyCounter} nWh"
                    averageCurrentTv.text = "${batteryData.propertyCurrentAverage} µA"
                    currentTv.text = "${batteryData.propertyCurrentNow} µA"
                     chargingStatusTv.text = BatteryUtil.getBatteryStatusText(batteryData.propertyStatus)
                    isChargingTv.text =if( batteryData.isCharging) "Yes" else "No"
                    chargeTimeRemTv.text = batteryData.chargeTimeRemaining?.toString() ?: "Unknown"
                }
            }
        }

    }
}