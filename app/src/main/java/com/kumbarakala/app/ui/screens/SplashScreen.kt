package com.kumbarakala.app.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kumbarakala.app.ui.theme.ClayBrown
import com.kumbarakala.app.ui.theme.Cream
import com.kumbarakala.app.ui.theme.Terracotta
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (visible) 1f else 0.8f, label = "splash")

    LaunchedEffect(Unit) {
        visible = true
        delay(1200)
        onFinished()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Cream, Terracotta))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
                .scale(scale)
                .background(ClayBrown, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("KK", color = Cream, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        }
        Text("Kumbara-Kala", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = ClayBrown)
        Text("Clay stories for the digital age", color = ClayBrown)
    }
}
