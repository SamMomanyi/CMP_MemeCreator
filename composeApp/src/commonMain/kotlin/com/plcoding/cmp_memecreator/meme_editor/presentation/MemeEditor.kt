package com.plcoding.cmp_memecreator.meme_editor.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.cmp_memecreator.core.theme.MemeCreatorTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MemeEditorScreenRoot(
    viewModel: MemeEditorViewModel = koinViewModel()
){
    val  state by ViewModel.state.collectAsStateWithLifecycle()
    MemeEditorScreen(
        state = state,
        onAction = viewModel::onAction
    )
}



//we only want to pass down two parameters , no matter how big the state grows
//we also avoid passing the viewModelInstance
//this also makes it easy for our screen to be previewed
@Composable
fun MemeEditorScreen(
    state: MemeEditorState,
    onAction: (MemeEditorAction) -> Unit
){

}

@Preview
@Composable
private fun Preview(){
    MemeCreatorTheme {
        MemeEditorScreen(
            state = MemeEditorState(),
            onAction = {}
        )
    }
}
