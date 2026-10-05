package com.example.appvuelos_capebe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Footer(
    modifier: Modifier = Modifier,
    copyrightText: String = "© 2026 CAPEBE. Todos los derechos reservados",
    backgroundColor: Color = Color(0xFF9ECDEB),
    textColor: Color = Color.Black
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .navigationBarsPadding()
            .height(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = copyrightText,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}