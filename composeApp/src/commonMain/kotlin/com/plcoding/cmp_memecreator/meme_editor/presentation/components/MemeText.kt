package com.plcoding.cmp_memecreator.meme_editor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import com.plcoding.cmp_memecreator.meme_editor.presentation.MemeText
import com.plcoding.cmp_memecreator.meme_editor.presentation.TextBoxInteractionState
import com.plcoding.cmp_memecreator.meme_editor.presentation.util.rememberFillTextStyle
import com.plcoding.cmp_memecreator.meme_editor.presentation.util.rememberStrokeTextStyle
import kotlinx.coroutines.delay

@Composable
fun MemeTextBox(
    memeText: MemeText,
    textBoxInteractionState: TextBoxInteractionState,
    maxWidth: Dp,
    maxHeight: Dp,
    onClick: () -> Unit,
    onDoubleClick: () -> Unit,
    onTextChange: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    //help us place the x icon right at the top right
    //also have another box inside it to hold the text
    //we also need to focus and put the cursor at the right spot and that works via focusRequester
    //we also need to add it as a modifier variable for the focus meme requester,for linkage
    val memeTextFocusRequester = remember {
        FocusRequester()
    }
    //the overall object for functionalities such as clearing focus
    val focusManger = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    //we will show the keyBoard after having focused our text field
    //the delay helps it appear efficiently
    LaunchedEffect(textBoxInteractionState){
        if(textBoxInteractionState is TextBoxInteractionState.Editing){
            memeTextFocusRequester.requestFocus()
            delay(100)
            keyboardController?.show()
        }
    }

    //if the memeText id(another text field) or we are not in focus state changes, then we want to
    LaunchedEffect(textBoxInteractionState, memeText.id){
        if(textBoxInteractionState is TextBoxInteractionState.Selected){
            focusManger.clearFocus()
        }
    }


    Box(modifier){
        Box(modifier = modifier
            .sizeIn(maxWidth = maxWidth, maxHeight = maxHeight)
            .border(
                width = 2.dp,
                color = if(textBoxInteractionState is TextBoxInteractionState.Selected ||
                    textBoxInteractionState is TextBoxInteractionState.Editing
                    ){
                    Color.White
                } else Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                color = if(textBoxInteractionState is TextBoxInteractionState.Editing){
                    Color.Black.copy(alpha = 0.15f)
                } else Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            )
            //we use a combinedclickable which has more functionalities us compared to one clickable
            .combinedClickable(
                onClick = onClick,
                onDoubleClick = onDoubleClick
            )
        ){
            val strokeTextStyle = rememberStrokeTextStyle()
            val fillTextStyle = rememberFillTextStyle()
            //padding between text and box
            val textPadding = 8.dp
            if(textBoxInteractionState is TextBoxInteractionState.Editing){
                //if editing we render the outlined textfield
                OutlinedImpactTextField(
                    text = memeText.text,
                    onTextChange = onTextChange,
                    strokeTextStyle = strokeTextStyle,
                    fillTextStyle = fillTextStyle,
                    maxWidth = maxWidth - (textPadding * 2),
                    maxHeight = maxHeight - (textPadding * 2),
                    modifier = Modifier
                        .focusRequester(memeTextFocusRequester)
                        .padding(textPadding)
                )
            } else {
                //we render our outlined imapct text without any editing capabilities
                OutlinedImpactText(
                    text = memeText.text,
                    strokeTextStyle = strokeTextStyle,
                    fillTextStyle = fillTextStyle,
                    modifier =  Modifier
                        .padding(textPadding)
                )
            }
        }
        //it is better to show in which state we want to show the X button rather than in which state we don't since if we added another interaction state we then wouldn't have to change multiple segments of our code
        if(textBoxInteractionState is TextBoxInteractionState.Selected ||
                textBoxInteractionState is TextBoxInteractionState.Editing ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    //12dp to the left and
                    .offset(
                        x = 12.dp,
                        y = -(12).dp
                    )
                    .clip(CircleShape)
                    .background(Color(0xFFB3261E))
                    .clickable{
                        onDeleteClick()
                    },
                contentAlignment = Alignment.Center

            ){
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }

        }
    }

}