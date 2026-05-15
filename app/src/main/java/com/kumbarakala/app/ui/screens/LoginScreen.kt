package com.kumbarakala.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kumbarakala.app.ui.theme.Cream
import com.kumbarakala.app.ui.theme.Sand
import com.kumbarakala.app.ui.theme.Terracotta
import com.kumbarakala.app.viewmodel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel, onAuthenticated: () -> Unit) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(state.isAuthenticated) {
        if (state.isAuthenticated) onAuthenticated()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Cream, Sand)))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Kumbara-Kala", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Text("Sign in to manage your clay-art brand", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(24.dp))
        Card(shape = RoundedCornerShape(8.dp)) {
            Column(Modifier.padding(18.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = state.email,
                    onValueChange = viewModel::updateEmail,
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = state.password,
                    onValueChange = viewModel::updatePassword,
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                state.error?.let {
                    Spacer(Modifier.height(8.dp))
                    Text(it, color = Terracotta)
                }
                Spacer(Modifier.height(16.dp))
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Button(onClick = viewModel::login, modifier = Modifier.fillMaxWidth()) {
                        Text("Login")
                    }
                    OutlinedButton(onClick = viewModel::register, modifier = Modifier.fillMaxWidth()) {
                        Text("Register")
                    }
                    OutlinedButton(onClick = viewModel::continueAsDemo, modifier = Modifier.fillMaxWidth()) {
                        Text("Continue Demo")
                    }
                }
            }
        }
    }
}
