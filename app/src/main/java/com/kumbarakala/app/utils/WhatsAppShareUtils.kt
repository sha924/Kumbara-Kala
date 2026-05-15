package com.kumbarakala.app.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object WhatsAppShareUtils {
    fun saveToCache(context: Context, bitmap: Bitmap): android.net.Uri {
        val file = File(context.cacheDir, "kumbara_story_card.png")
        FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }

    fun shareStoryCard(context: Context, bitmap: Bitmap, caption: String) {
        val uri = saveToCache(context, bitmap)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_TEXT, caption)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            setPackage("com.whatsapp")
        }
        runCatching { context.startActivity(intent) }.onFailure {
            context.startActivity(Intent.createChooser(intent.apply { setPackage(null) }, "Share story card"))
        }
    }
}
