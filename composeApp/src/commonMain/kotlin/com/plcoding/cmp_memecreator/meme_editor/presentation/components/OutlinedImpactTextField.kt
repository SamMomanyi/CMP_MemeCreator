package com.plcoding.cmp_memecreator.meme_editor.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.plcoding.cmp_memecreator.meme_editor.presentation.util.rememberFillTextStyle
import com.plcoding.cmp_memecreator.meme_editor.presentation.util.rememberStrokeTextStyle

@Composable
fun OutlinedImpactTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    strokeTextStyle: TextStyle = rememberStrokeTextStyle(),
    fillTextStyle: TextStyle = rememberFillTextStyle(),
    maxWidth: Dp? = null,
    maxHeight: Dp? = null
    ){
    //measures a text and how much field it occupies

    val measurer = rememberTextMeasurer()
    val constraints = calculateTextConstraints(maxWidth,maxHeight)
    val textLayoutResult = remember(text, strokeTextStyle,constraints) {
        measurer.measure(
            text = text,
            style = strokeTextStyle,
            constraints = constraints
        )
    }

    val textBoundingSize = with(LocalDensity.current) {
        DpSize(
            width = textLayoutResult.size.width.toDp(),
            height = textLayoutResult.size.height.toDp()
        )
    }

    BasicTextField(
        //we read the value from viewModel
        value = text,
        onValueChange = {
            //we only support uppercase
            onTextChange(it.uppercase())
        },
        //the color of our cursor
        cursorBrush = SolidColor(Color.White),
        singleLine = false,
        textStyle = fillTextStyle,
        //we use a decorationBox which allows us to sort of draw to texts on each other
        decorationBox = { innerTextField ->
            //the actual text content from viewModel
            Text(
                text = text,
                style = strokeTextStyle
            )
            //what is being rendered
            innerTextField()
        },
        modifier = Modifier
            .size(textBoundingSize)
    )
}

@Composable
private fun calculateTextConstraints(maxWidth: Dp?, maxHeight: Dp?) : Constraints {
    val density = LocalDensity.current
    //we check if we have provided any maxWidth or maxHeight else , we allow it to fully scale
    return with(density){
        Constraints(
            maxWidth = maxWidth?.roundToPx() ?: Int.MAX_VALUE,
            maxHeight = maxHeight?.roundToPx() ?: Int.MAX_VALUE
        )
    }

}