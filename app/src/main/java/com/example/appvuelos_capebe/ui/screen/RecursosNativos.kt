package com.example.appvuelos_capebe.ui.screen

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.BatteryManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.appvuelos_capebe.ui.components.Footer
import com.example.appvuelos_capebe.ui.components.Header
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun RecursosNativos(
    onNavigateBack: () -> Unit
) {
    // LocalContext
    val context = LocalContext.current

    Scaffold(
        topBar = { Header("RECURSOS NATIVOS PISTA") },
        bottomBar = { Footer() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Recursos nativos con Compose",
                style = MaterialTheme.typography.headlineSmall
            )

            TarjetaLinterna(context)
            TarjetaVibracion(context)
            TarjetaBateria(context)

            OutlinedButton(
                onClick = { onNavigateBack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al Menu")
            }
        }
    }
}

// linterna
@Composable
fun TarjetaLinterna(context: Context) {
    var encendida by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Linterna", style = MaterialTheme.typography.titleMedium)
            Switch(
                checked = encendida,
                onCheckedChange = { nuevoEstado ->
                    if (cambiarLinterna(context, nuevoEstado)) {
                        encendida = nuevoEstado
                    }
                }
            )
        }
    }
}

fun cambiarLinterna(context: Context, encender: Boolean): Boolean {
    val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    val idCamara = cameraManager.cameraIdList.firstOrNull { id ->
        cameraManager.getCameraCharacteristics(id)
            .get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
    } ?: return false

    cameraManager.setTorchMode(idCamara, encender)
    Log.d("Linterna", "Luz en camara $idCamara -> ${if (encender) "ENCENDIDA" else "APAGADA"}")
    return true
}

// bateria
@Composable
fun TarjetaBateria(context: Context) {
    var nivel by remember { mutableStateOf(leerBateria(context)) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Bateria", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Text("Nivel actual: $nivel %")
            Spacer(Modifier.height(8.dp))
            Button(onClick = { nivel = leerBateria(context) }) {
                Text("Actualizar")
            }
        }
    }
}

fun leerBateria(context: Context): Int {
    val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
}

// vibracion
@Composable
fun TarjetaVibracion(context: Context) {
    val duracion = 500L
    var vibrando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // animacion de temblor
    val transicion = rememberInfiniteTransition(label = "temblor")
    val desplazamiento by transicion.animateFloat(
        initialValue = -8f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(tween(40), RepeatMode.Reverse),
        label = "desplazamiento"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .offset { IntOffset(if (vibrando) desplazamiento.roundToInt() else 0, 0) },
        colors = CardDefaults.cardColors(
            containerColor = if (vibrando) MaterialTheme.colorScheme.tertiaryContainer
            else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Vibracion", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Text(if (vibrando) "Vibrando..." else "En reposo")
            Spacer(Modifier.height(8.dp))
            Button(
                enabled = !vibrando,
                onClick = {
                    vibrar(context, duracion)
                    Toast.makeText(context, "Vibrando $duracion ms", Toast.LENGTH_SHORT).show()
                    scope.launch {
                        vibrando = true
                        delay(duracion)
                        vibrando = false
                    }
                }
            ) {
                Text("Vibrar $duracion ms")
            }
        }
    }
}

fun vibrar(context: Context, milisegundos: Long) {
    val vibrador: Vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        manager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrador.vibrate(
            VibrationEffect.createOneShot(milisegundos, VibrationEffect.DEFAULT_AMPLITUDE)
        )
    } else {
        @Suppress("DEPRECATION")
        vibrador.vibrate(milisegundos)
    }
    Log.d("Vibracion", "Vibrando $milisegundos ms")
}