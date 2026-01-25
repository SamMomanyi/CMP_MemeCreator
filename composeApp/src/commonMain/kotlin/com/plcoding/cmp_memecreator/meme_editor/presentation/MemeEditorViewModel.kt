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
            is MemeEditorAction.OnDeleteMemeTextClick -> deleteMemeText(action.id)
            MemeEditorAction.OnDismissLeaveWithoutSaving -> TODO()
            is MemeEditorAction.OnEditMemeText -> editMemeText(action.id)
            MemeEditorAction.OnGoBackClick -> TODO()
            is MemeEditorAction.OnMemeTextChange -> updateMemeText(action.id,action.text)
            is MemeEditorAction.OnMemeTextTransformChange -> TODO()
            is MemeEditorAction.OnSaveMemeClick -> TODO()
            is MemeEditorAction.OnSelectMemeText -> selectMemeText(action.id)
            MemeEditorAction.OnTapOutsideSelectedText -> TODO()
        }
    }

    private fun deleteMemeText(id: String) {
        //so we take our exisiting memeTexts and filter them accordingly
        _state.update { it.copy(
            memeTexts = it.memeTexts.filter { memeText ->
                memeText.id != id
            }
        )

        }
    }

    private fun selectMemeText(id: String){
        _state.update {
            it.copy(
                textBoxInteractioState = TextBoxInteractionState.Selected(id )
            )
        }
    }

    private fun updateMemeText(id: String, text: String) {
        _state.update {
            //if the current text is equal to our textID
            it.copy(
                memeTexts = it.memeTexts.map {  memeText ->
                    if(memeText.id == id){
                        memeText.copy(text = text)
                    } else memeText
                }
            ) }
    }

    private fun editMemeText(id: String) {
        _state.update {
            it.copy(
                textBoxInteractioState = TextBoxInteractionState.Editing(id)
            )
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