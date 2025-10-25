package com.plcoding.cmp_memecreator.meme_editor.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cmp_memecreator.core.theme.Fonts

@Composable
fun rememberFillTextStyle(
    fontSize: TextUnit = 36.sp,
    fontFamily: FontFamily = Fonts.Impact,
    fillColor: Color = Color.White,
    textAlign: TextAlign = TextAlign.Center
): TextStyle {
    return remember(fontSize, fontFamily, fillColor, textAlign) {
        TextStyle(
            color = fillColor,
            textAlign = textAlign,
            fontSize = fontSize,
            fontFamily = fontFamily
        )
    }
}