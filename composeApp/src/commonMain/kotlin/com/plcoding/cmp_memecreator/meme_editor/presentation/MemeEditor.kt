package com.plcoding.cmp_memecreator.meme_editor.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cmpmemecreator.composeapp.generated.resources.Res
import cmpmemecreator.composeapp.generated.resources.meme_template_06
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.plcoding.cmp_memecreator.core.presentation.MemeTemplate
import com.plcoding.cmp_memecreator.core.theme.MemeCreatorTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MemeEditorScreenRoot(
    template: MemeTemplate,
    viewModel: MemeEditorViewModel = koinViewModel()
){
    val  state by viewModel.state.collectAsStateWithLifecycle()
    MemeEditorScreen(
        template = template,
        state = state,
        onAction = viewModel::onAction
    )
}



//we only want to pass down two parameters , no matter how big the state grows
//we also avoid passing the viewModelInstance
//this also makes it easy for our screen to be previewed
@Composable
fun MemeEditorScreen(
    //we take in the template selected as a navigation thingy
    template :  MemeTemplate,
    state: MemeEditorState,
    onAction: (MemeEditorAction) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(template.drawable),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
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
