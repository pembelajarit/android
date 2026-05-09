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
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import kotlin.math.abs

class AccelerometerFragment : Fragment(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    private lateinit var tvX: TextView
    private lateinit var tvY: TextView
    private lateinit var tvZ: TextView
    private lateinit var tvTilt: TextView
    private lateinit var tvShake: TextView

    private var lastTime = 0L
    private var lastX = 0f; private var lastY = 0f; private var lastZ = 0f
    private val SHAKE_THRESHOLD = 800

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_accelerometer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvX     = view.findViewById(R.id.tvX)
        tvY     = view.findViewById(R.id.tvY)
        tvZ     = view.findViewById(R.id.tvZ)
        tvTilt  = view.findViewById(R.id.tvTilt)
        tvShake = view.findViewById(R.id.tvShake)

        sensorManager = requireActivity().getSystemService(android.content.Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accelerometer == null) tvShake.text = "⚠️ Sensor tidak tersedia"
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        tvX.text = "X: %.2f m/s²".format(x)
        tvY.text = "Y: %.2f m/s²".format(y)
        tvZ.text = "Z: %.2f m/s²".format(z)

        tvTilt.text = when {
            y > 7  -> "⬆️ Miring ke Atas"
            y < -7 -> "⬇️ Miring ke Bawah"
            x > 7  -> "➡️ Miring ke Kanan"
            x < -7 -> "⬅️ Miring ke Kiri"
            else   -> "📱 Posisi Datar"
        }

        // Deteksi shake
        val now = System.currentTimeMillis()
        if (now - lastTime > 100) {
            val diff = now - lastTime
            lastTime = now
            val speed = abs(x + y + z - lastX - lastY - lastZ) / diff * 10000
            if (speed > SHAKE_THRESHOLD) tvShake.text = "📳 Shake Terdeteksi!"
            else if (tvShake.text.startsWith("📳")) tvShake.text = "Gerakkan HP untuk mendeteksi shake"
            lastX = x; lastY = y; lastZ = z
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}