package com.plcoding.cmp_memecreator.meme_editor.presentation

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MemeEditorViewModel : ViewModel() {

    private val _state = MutableStateFlow(MemeEditorState())

    val state : StateFlow<MemeEditorState> = _state.collectAsState()


    fun onAction(action: MemeEditorAction){
        when(action){
            MemeEditorAction.OnAddTextClick -> TODO()
            MemeEditorAction.OnConfirmLeaveWithoutSaving -> TODO()
            is MemeEditorAction.OnContainerSizeChange -> updateContainerSize(action.size)
            is MemeEditorAction.OnDeleteMemeTextClick -> TODO()
            MemeEditorAction.OnDismissLeaveWithoutSaving -> TODO()
            is MemeEditorAction.OnEditMemeText -> TODO()
            MemeEditorAction.OnGoBackClick -> TODO()
            is MemeEditorAction.OnMemeTextChange -> TODO()
            is MemeEditorAction.OnMemeTextTransformChange -> TODO()
            is MemeEditorAction.OnSaveMemeClick -> TODO()
            is MemeEditorAction.OnSelectMemeText -> TODO()
            MemeEditorAction.OnTapOutsideSelectedText -> TODO()
        }
    }

    private fun updateContainerSize(size : IntSize){
        _state.update {
            it.copy(
                templateSize = size
            )
        }
    }
}