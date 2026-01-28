package com.plcoding.cmp_memecreator.meme_editor.presentation.util

import android.content.res.Resources
import androidx.compose.ui.unit.TextUnit
import android.util.TypedValue

actual fun TextUnit.toPx(): Float {
   val metrics = Resources.getSystem().displayMetrics

    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.value,
        metrics
    )
}