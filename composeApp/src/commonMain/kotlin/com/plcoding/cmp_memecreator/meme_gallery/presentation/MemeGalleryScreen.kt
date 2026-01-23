package com.plcoding.cmp_memecreator.meme_gallery.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cmpmemecreator.composeapp.generated.resources.Res
import cmpmemecreator.composeapp.generated.resources.meme_templates
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.plcoding.cmp_memecreator.core.presentation.MemeTemplate
import com.plcoding.cmp_memecreator.core.presentation.memeTemplates
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MemeGalleryScreen(
    onMemeTemplateSelected : (MemeTemplate) -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.meme_templates))
                }
            )
        }
    ){innerPadding ->
        //a staggered adaptive ensures each item is give 150.dp then if space left isn't enough for a third item it will relocate the left space efficientl
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(minSize = 150.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp,
            //space around the whole grid is called contentPadding(that is the window inset oadding)
            contentPadding = PaddingValues(
                //so apart from the innerPadding we add our own aesthetic 8.dp of padding,
                //we also calculate the padding incase the phone has a corner
                start = innerPadding.calculateLeftPadding(LayoutDirection.Ltr) + 8.dp,
                end = innerPadding.calculateRightPadding(LayoutDirection.Ltr) + 8.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 8.dp,
            ),
        ){
            items(
                //the meme template static list we created
                items = memeTemplates,
                key = {it.id }
            ){ memeTemplate ->
                Card(
                    onClick = { onMemeTemplateSelected(memeTemplate) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Image(
                        painter = painterResource(memeTemplate.drawable),
                        modifier = Modifier,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null
                    )
                }
            }
        }
    }
}