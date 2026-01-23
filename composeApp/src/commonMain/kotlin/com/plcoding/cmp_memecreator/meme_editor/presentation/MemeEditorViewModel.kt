package com.plcoding.cmp_memecreator.meme_editor.presentation

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MemeEditorViewModel : ViewModel() {

    private val _state = MutableStateFlow(MemeEditorState())

    private var hasLoadedInitialData = false


    val state : StateFlow<MemeEditorState> = _state
        .onStart{
        if(!hasLoadedInitialData){
            hasLoadedInitialData = true
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MemeEditorState()
        )

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