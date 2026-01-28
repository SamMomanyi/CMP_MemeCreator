package com.plcoding.cmp_memecreator.meme_editor.presentation.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import com.plcoding.cmp_memecreator.meme_editor.presentation.MemeText
import kotlin.math.roundToInt

class MemeRenderCalculator(
    //we need a value to convert between dp values on our UI and actual pixels to render on canvas
    private val density: Float
) {
    companion object {
        //since when text is selected we add room e.g the border
        private const val TEXT_PADDING_DP = 8f
        private val STROKE_WIDTH_DP = 3f
    }

    fun calculateScaleFactors(
        bitmapWidth: Int,
        bitmapHeight: Int,
        templateSize: IntSize
    ) : ScaleFactors{
        //this gives our scale factor for x
        // if the bitmap ration doesn't have the same scale as our aspect ration
        val scaleX = if(templateSize.width > 0) bitmapWidth.toFloat() / templateSize.width else 1f
        val scaleY = if(templateSize.height > 0) bitmapHeight.toFloat() / templateSize.height else 1f

        //which is simply the value between those two
        val bitmapScale = (scaleX + scaleY) / 2

        return ScaleFactors(
            scaleX = scaleX,
            scaleY = scaleY,
            bitmapScale = bitmapScale
        )
    }

     fun calculateScaledMemeText(
        memeText: MemeText,
        scaleFactors: ScaleFactors,
        templateSize: IntSize
    ): ScaledMemeText {
        //we extract the properties
        val (scaleX, scaleY, bitmapScale) = scaleFactors

        val scaledOffset = Offset(
            x = (memeText.offsetRatioX * templateSize.width) * scaleX,
            y = (memeText.offsetRatioY * templateSize.height) * scaleY
        )

        //we want to know the textPadding in pixels
        val textPaddingPx = TEXT_PADDING_DP * density
        val textPaddingBitmapX = textPaddingPx * scaleX
        val textPaddingBitmapY = textPaddingPx * scaleY

        val scaledFontSize = memeText.fontSize * bitmapScale

        val strokeWidth = STROKE_WIDTH_DP * density * scaleX

        val paddingDp = TEXT_PADDING_DP * 2
        val paddingPx = paddingDp * density
        val constraintWidth = ((templateSize.width / memeText.scale) * scaleX - paddingPx * scaleX)
            .roundToInt()
            .coerceAtLeast(1) //make it have atleast on positive value

        return ScaledMemeText(
            text = memeText.text,
            scaledOffset = scaledOffset,
            scaledFontSizePx = scaledFontSize.toPx(),
            strokeWidth = strokeWidth,
            constraintWidth = constraintWidth,
            textPaddingX = textPaddingBitmapX,
            textPaddingY = textPaddingBitmapY,
            rotation = memeText.rotation,
            scale = memeText.scale,
            originalText = memeText
        )
    }
}

data class ScaleFactors(
    val scaleX: Float,
    val scaleY:Float,
    val bitmapScale:Float

)