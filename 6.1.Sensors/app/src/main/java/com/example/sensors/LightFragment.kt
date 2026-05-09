package com.example.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment

class LightFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null

    private lateinit var tvLux: TextView
    private lateinit var tvLevel: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_light, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvLux     = view.findViewById(R.id.tvLux)
        tvLevel   = view.findViewById(R.id.tvLevel)
        progressBar = view.findViewById(R.id.progressLight)

        sensorManager = requireActivity().getSystemService(android.content.Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        if (lightSensor == null) tvLevel.text = "⚠️ Sensor cahaya tidak tersedia"
    }

    override fun onResume() {
        super.onResume()
        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val lux = event.values[0]  // satuan: lux

        tvLux.text = "Cahaya: %.1f lux".format(lux)

        // Klasifikasi level cahaya
        val (level, emoji) = when {
            lux < 10    -> "Sangat Gelap" to "🌑"
            lux < 100   -> "Gelap / Redup" to "🌘"
            lux < 500   -> "Cahaya Dalam Ruangan" to "💡"
            lux < 1000  -> "Terang" to "🌤️"
            lux < 10000 -> "Sangat Terang" to "☀️"
            else        -> "Sinar Matahari Langsung" to "🌞"
        }
        tvLevel.text = "$emoji $level"

        // Update progress bar (max 10000 lux)
        progressBar.progress = (lux / 10000f * 100).toInt().coerceIn(0, 100)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}