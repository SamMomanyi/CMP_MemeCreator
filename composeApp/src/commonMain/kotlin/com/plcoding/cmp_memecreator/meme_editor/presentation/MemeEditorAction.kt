package com.plcoding.cmp_memecreator.meme_editor.presentation

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import com.plcoding.cmp_memecreator.core.presentation.MemeTemplate

sealed interface MemeEditorAction {
    data object OnGoBackClick : MemeEditorAction
    data object OnConfirmLeaveWithoutSaving :  MemeEditorAction
    data object OnDismissLeaveWithoutSaving : MemeEditorAction

    data class OnSaveMemeClick(val memeTemplate: MemeTemplate): MemeEditorAction
    //clear the selection focus
    data object OnTapOutsideSelectedText : MemeEditorAction

    data object OnAddTextClick : MemeEditorAction
    data class OnSelectMemeText(val id: String) : MemeEditorAction //entering the selection state
    data class OnEditMemeText(val id: String) : MemeEditorAction //entering the edit state
    data class OnMemeTextChange(val id: String,val text : String) : MemeEditorAction //changing the text
    data class OnDeleteMemeTextClick(val id: String): MemeEditorAction

    data class OnMemeTextTransformChange(
        val id: String,
        val offset: Offset,//(consists of an x and y value)
        val rotation : Float,
        val scale : Float
    ) : MemeEditorAction

    //whenever the container changes
    data class OnContainerSizeChange(val size : IntSize) : MemeEditorAction
}