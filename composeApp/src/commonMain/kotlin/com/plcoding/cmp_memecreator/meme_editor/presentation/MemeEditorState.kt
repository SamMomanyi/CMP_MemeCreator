package com.plcoding.cmp_memecreator.meme_editor.presentation

import androidx.compose.ui.unit.IntSize

//quite a number of states here ,
//the text editor could be selected, the text init could change,the dialog box could show,the
data class MemeEditorState(
    val templateSize: IntSize = IntSize.Zero,
    val isLeavingWithoutSaving: Boolean = false,
    val textBoxInteractioState: TextBoxInteractionState = TextBoxInteractionState.None,
    //here we know have a list of memeTexts
    val memeTexts : List<MemeText> = listOf(),
    //we can use this without events since if we pop back then the viewModel state of the MemeEditor is destroyed with it
    val hasLeftEditor: Boolean = false
)


//the textbox could be selected , not selected, selected and being edited , also which textbox
sealed interface TextBoxInteractionState {
    data object None : TextBoxInteractionState
    data class Selected(val textBoxId: String) : TextBoxInteractionState
    data class Editing(val textBoxId: String) : TextBoxInteractionState
}

