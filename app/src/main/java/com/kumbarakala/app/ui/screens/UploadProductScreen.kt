package com.kumbarakala.app.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kumbarakala.app.data.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadProductScreen(onBack: () -> Unit, onSave: (Product, Uri?) -> Unit) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Decor") }
    var description by remember { mutableStateOf("") }
    var health by remember { mutableStateOf("") }
    var eco by remember { mutableStateOf("") }
    var artisan by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val picker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri = it }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Upload Product") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, contentDescription = "Back") } }
            )
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Field(name, { name = it }, "Product name")
            Field(price, { price = it }, "Price")
            Field(category, { category = it }, "Category")
            Field(description, { description = it }, "Description")
            Field(health, { health = it }, "Health benefits")
            Field(eco, { eco = it }, "Eco benefits")
            Field(artisan, { artisan = it }, "Artisan name")
            Field(village, { village = it }, "Village name")
            Spacer(Modifier.height(10.dp))
            OutlinedButton(onClick = { picker.launch("image/*") }, modifier = Modifier.fillMaxWidth()) {
                Text(if (imageUri == null) "Pick Product Image" else "Image Selected")
            }
            Button(
                onClick = {
                    onSave(
                        Product(
                            name = name.ifBlank { "Untitled Clay Product" },
                            price = price.toDoubleOrNull() ?: 0.0,
                            category = category,
                            description = description,
                            healthBenefits = health,
                            ecoBenefits = eco,
                            artisanName = artisan,
                            villageName = village
                        ),
                        imageUri
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Product")
            }
        }
    }
}

@Composable
private fun Field(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
    )
}
