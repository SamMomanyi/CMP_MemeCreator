package com.plcoding.cmp_memecreator.meme_editor.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cmpmemecreator.composeapp.generated.resources.Res
import cmpmemecreator.composeapp.generated.resources.cancel
import cmpmemecreator.composeapp.generated.resources.leave
import cmpmemecreator.composeapp.generated.resources.leave_editor_message
import cmpmemecreator.composeapp.generated.resources.leave_editor_title
import cmpmemecreator.composeapp.generated.resources.meme_template_06
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.plcoding.cmp_memecreator.core.presentation.MemeTemplate
import com.plcoding.cmp_memecreator.core.theme.MemeCreatorTheme
import com.plcoding.cmp_memecreator.meme_editor.presentation.components.BottomBar
import com.plcoding.cmp_memecreator.meme_editor.presentation.components.ConfirmationDialog
import com.plcoding.cmp_memecreator.meme_editor.presentation.components.ConfirmationDialogConfig
import com.plcoding.cmp_memecreator.meme_editor.presentation.components.DraggableContainer
import com.plcoding.cmp_memecreator.meme_editor.presentation.components.MemeTextBox
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MemeEditorScreenRoot(
    template: MemeTemplate,
    onGoBack : () -> Unit,
    viewModel : MemeEditorViewModel = koinViewModel()
){
    val  state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.hasLeftEditor){
        if (state.hasLeftEditor){
            onGoBack()
        }
    }
    MemeEditorScreen(
        template = template,
        state = state,
        //we can intercept all the actions here
        onAction = viewModel::onAction
    )
}



//we only want to pass down two parameters , no matter how big the state grows
//we also avoid passing the viewModelInstance
//this also makes it easy for our screen to be previewed
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MemeEditorScreen(
    //we take in the template selected as a navigation thingy
    template :  MemeTemplate,
    state: MemeEditorState,
    onAction: (MemeEditorAction) -> Unit
) {
    BackHandler(
        //ensures it won't always be enabled
        enabled = !state.isLeavingWithoutSaving
    ) {
        onAction(MemeEditorAction.OnGoBackClick)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                //when we tap outside the selected screen
                detectTapGestures {
                    onAction(MemeEditorAction.OnTapOutsideSelectedText)
                }
            },
        bottomBar = {
            BottomAppBar(
                {
                    BottomBar(
                        onAddTextClick = {
                            onAction(MemeEditorAction.OnAddTextClick)
                        },
                        onSaveClick = {
                            onAction(MemeEditorAction.OnSaveMemeClick(template))
                        }
                    )
                }
            )
        }
    ) { innerPadding ->

        //the top box fillMaxSize but the next one aligns itself to how much the text is
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box{
                // The constraints are now available here as maxWidth and maxHeight

                // 2. The Background Image (Bottom Layer)
                Image(
                    painter = painterResource(template.drawable),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        //when size changes we notify viewModel about the size change
                        .onSizeChanged {
                            onAction(
                                MemeEditorAction.OnContainerSizeChange(
                                    it
                                )
                            )
                        },
                    contentScale = ContentScale.FillWidth
                )

                // 3. The Text Layer (Top Layer)
                // Iterate over the list of texts in your state

                DraggableContainer(
                    children = state.memeTexts,
                    textBoxInteractionState = state.textBoxInteractioState,
                    onChildTransformChanged = { id, offset, rotation, scale ->
                        onAction(
                            MemeEditorAction.OnMemeTextTransformChange(
                                id = id,
                                offset = offset,
                                rotation = rotation,
                                scale = scale
                            )
                        )
                    },
                    onChildClick = {
                        onAction(
                            MemeEditorAction.OnSelectMemeText(it)
                        )
                    },
                    onChildDoubleClick = {
                        onAction(MemeEditorAction.OnEditMemeText(it))
                    },
                    onChildTextChange = { id, text ->
                        onAction(MemeEditorAction.OnMemeTextChange(id,text))
                    },
                    onChildDeleteClick = {
                        onAction(MemeEditorAction.OnDeleteMemeTextClick(it))
                    },
                    //this is where it extends as match as the parent Box which doesn't fillmax Size
                    modifier = Modifier
                        .matchParentSize()
                )
            }
            IconButton(
                onClick = {
                    onAction(MemeEditorAction.OnGoBackClick)
                },
                modifier = Modifier
                    .align(Alignment.TopStart)
            ){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
                    contentDescription = "Back"
                )
            }
        }
    }
    if(state.isLeavingWithoutSaving){
        ConfirmationDialog(
            config = ConfirmationDialogConfig(
                title = stringResource(Res.string.leave_editor_title),
                message = stringResource(Res.string.leave_editor_message),
                confirmButtonText = stringResource(Res.string.leave),
                cancelButtonText = stringResource(Res.string.cancel),
                confirmButtonColor = MaterialTheme.colorScheme.secondary
            ),
            onConfirm = {
                onAction(MemeEditorAction.OnConfirmLeaveWithoutSaving)
            },
            onDismiss = {
                onAction(MemeEditorAction.OnDismissLeaveWithoutSaving)
            }
        )
    }
}

@Preview
@Composable
private fun Preview(){
    MemeCreatorTheme {
        MemeEditorScreen(
            template = MemeTemplate(
                id = "meme_template_01",
                drawable = Res.drawable.meme_template_06
            ),
            state = MemeEditorState(),
            onAction = {}
        )
    }
}
