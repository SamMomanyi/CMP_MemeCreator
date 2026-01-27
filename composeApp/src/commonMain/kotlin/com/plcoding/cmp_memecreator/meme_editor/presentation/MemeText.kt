package com.plcoding.cmp_memecreator.meme_editor.presentation

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class MemeText(
    val id : String,
    val text : String,
    val fontSize: TextUnit  = 36.sp,
    //it is important for as to not choose default values since the offset value can change upon rotation
    val offsetRatioX: Float = 0f,
    val offsetRatioY: Float = 0f,
    val rotation: Float = 0f,
    val scale: Float = 1f
)
