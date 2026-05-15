package com.kumbarakala.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.FirebaseApp
import com.kumbarakala.app.navigation.KumbaraNavigation
import com.kumbarakala.app.ui.theme.KumbaraKalaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runCatching { FirebaseApp.initializeApp(this) }
        enableEdgeToEdge()
        setContent {
            KumbaraKalaTheme {
                KumbaraNavigation()
            }
        }
    }
}
