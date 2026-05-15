package com.kumbarakala.app.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.kumbarakala.app.data.model.Product
import com.kumbarakala.app.utils.StoryCardGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StoryCardViewModel : ViewModel() {
    private val _bitmap = MutableStateFlow<Bitmap?>(null)
    val bitmap: StateFlow<Bitmap?> = _bitmap.asStateFlow()

    fun generate(context: Context, product: Product) {
        _bitmap.value = StoryCardGenerator.generate(context, product)
    }
}
