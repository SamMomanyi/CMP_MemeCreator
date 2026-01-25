package com.plcoding.cmp_memecreator.meme_editor.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.plcoding.cmp_memecreator.core.theme.MemeCreatorTheme
import com.plcoding.cmp_memecreator.meme_editor.presentation.util.rememberStrokeTextStyle
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OutlinedImpactText(
    text: String,
    strokeTextStyle: TextStyle,
    fillTextStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    //we are going to draw two texts one with a field and another with a stroke since a text can t have both
    Box(
        modifier = modifier
    ){
        //we first render the stroke text
        Text(
            text = text,
            style = strokeTextStyle
        )
        Text(
            text = text,
            style = fillTextStyle
        )
    }
}

@Composable
@Preview
fun OutlinedImpactTextPreview(){
    MemeCreatorTheme {
        OutlinedImpactText(
           text = "HELLO WORLD",
            strokeTextStyle = rememberStrokeTextStyle()

        )
    }
}