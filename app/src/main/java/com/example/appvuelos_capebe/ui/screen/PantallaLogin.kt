package com.example.appvuelos_capebe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appvuelos_capebe.R

@Composable
fun PantallaLogin (){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9ECDEB)),
        contentAlignment = Alignment.Center

    ){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagen_portada_capebe),
            contentDescription = "Logo CAPEBE",
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {
            Toast.makeText(context, "¡Boton funcionando!", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Ingresar ")
        }

      }
    }
}
