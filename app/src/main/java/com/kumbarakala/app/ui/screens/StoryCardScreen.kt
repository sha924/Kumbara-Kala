package com.kumbarakala.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kumbarakala.app.data.model.Product
import com.kumbarakala.app.utils.WhatsAppShareUtils
import com.kumbarakala.app.viewmodel.StoryCardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoryCardScreen(product: Product?, viewModel: StoryCardViewModel) {
    val context = LocalContext.current
    val bitmap by viewModel.bitmap.collectAsStateWithLifecycle()

    Scaffold(topBar = { TopAppBar(title = { Text("Story Card Generator") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            if (product == null) {
                Text("Open a product first to generate its story card.")
                return@Column
            }
            Text(product.name, style = MaterialTheme.typography.headlineSmall)
            Text("${product.artisanName} • ${product.villageName}")
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = { viewModel.generate(context, product) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate Story Card")
            }
            bitmap?.let { generated ->
                Spacer(Modifier.height(16.dp))
                Card {
                    Image(
                        bitmap = generated.asImageBitmap(),
                        contentDescription = "Generated story card",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = {
                        WhatsAppShareUtils.shareStoryCard(
                            context,
                            generated,
                            "Discover ${product.name}, handmade by ${product.artisanName} from ${product.villageName}."
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Share on WhatsApp")
                }
                OutlinedButton(
                    onClick = { WhatsAppShareUtils.saveToCache(context, generated) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Download Story Card")
                }
            }
        }
    }
}
