package com.example.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProximityFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var proximitySensor: Sensor? = null

    private lateinit var tvDistance: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_proximity, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvDistance = view.findViewById(R.id.tvDistance)
        tvStatus   = view.findViewById(R.id.tvStatus)
        tvInfo     = view.findViewById(R.id.tvInfo)

        sensorManager = requireActivity().getSystemService(android.content.Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (proximitySensor != null) {
            // Tampilkan jarak maksimal yang bisa dideteksi
            tvInfo.text = "Jangkauan max: ${proximitySensor!!.maximumRange} cm"
        } else {
            tvStatus.text = "⚠️ Sensor proximity tidak tersedia"
        }
    }

    override fun onResume() {
        super.onResume()
        proximitySensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val distance = event.values[0]
        val maxRange = proximitySensor?.maximumRange ?: 5f

        tvDistance.text = "Jarak: $distance cm"

        // Banyak HP hanya punya 2 nilai: 0 (dekat) atau max (jauh)
        if (distance < maxRange) {
            tvStatus.text = "🟥 NEAR — Objek Dekat!"
        } else {
            tvStatus.text = "🟩 FAR — Tidak Ada Objek"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}