package com.plcoding.cmp_memecreator.app

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.plcoding.cmp_memecreator.core.presentation.NavigationRoot
import com.plcoding.cmp_memecreator.core.theme.MemeCreatorTheme
import com.plcoding.cmp_memecreator.meme_gallery.presentation.MemeGalleryScreen

import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    MemeCreatorTheme{
        NavigationRoot()
    }
}