package com.kumbarakala.app.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.StaticLayout
import android.text.TextPaint
import com.kumbarakala.app.data.model.Product

object StoryCardGenerator {
    fun generate(context: Context, product: Product): Bitmap {
        val width = 1080
        val height = 1920
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val cream = Color.rgb(248, 231, 199)
        val terracotta = Color.rgb(166, 79, 42)
        val brown = Color.rgb(90, 48, 26)
        val green = Color.rgb(78, 107, 58)

        canvas.drawColor(cream)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 30f
        paint.color = terracotta
        canvas.drawRoundRect(RectF(45f, 45f, width - 45f, height - 45f), 48f, 48f, paint)

        paint.style = Paint.Style.FILL
        paint.color = terracotta
        canvas.drawRoundRect(RectF(120f, 180f, width - 120f, 760f), 44f, 44f, paint)
        paint.color = Color.rgb(226, 196, 155)
        canvas.drawCircle(width / 2f, 470f, 170f, paint)
        paint.color = brown
        canvas.drawOval(RectF(360f, 430f, 720f, 620f), paint)
        paint.color = terracotta
        canvas.drawRect(430f, 330f, 650f, 500f, paint)

        drawCentered(canvas, "Kumbara-Kala", 118f, 930f, brown, true)
        drawCentered(canvas, product.name, 68f, 1040f, terracotta, true)
        drawWrapped(canvas, "Health: ${product.healthBenefits}", 120f, 1155f, width - 240, 44f, brown)
        drawWrapped(canvas, "Eco: ${product.ecoBenefits}", 120f, 1340f, width - 240, 44f, green)
        drawCentered(canvas, "Made by ${product.artisanName}", 48f, 1620f, brown, true)
        drawCentered(canvas, product.villageName, 44f, 1690f, terracotta, false)
        drawCentered(canvas, "Handmade clay heritage, ready to share.", 36f, 1810f, brown, false)
        return bitmap
    }

    private fun drawCentered(canvas: Canvas, text: String, size: Float, y: Float, color: Int, bold: Boolean) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.color = color
            textSize = size
            textAlign = Paint.Align.CENTER
            typeface = if (bold) android.graphics.Typeface.DEFAULT_BOLD else android.graphics.Typeface.DEFAULT
        }
        canvas.drawText(text.take(34), canvas.width / 2f, y, paint)
    }

    private fun drawWrapped(canvas: Canvas, text: String, x: Float, y: Float, width: Int, size: Float, color: Int) {
        val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            this.color = color
            textSize = size
        }
        StaticLayout.Builder.obtain(text, 0, text.length, paint, width)
            .setLineSpacing(8f, 1f)
            .build()
            .also {
                canvas.save()
                canvas.translate(x, y)
                it.draw(canvas)
                canvas.restore()
            }
    }
}
