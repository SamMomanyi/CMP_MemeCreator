package com.plcoding.cmp_memecreator.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.cmp_memecreator.meme_editor.presentation.MemeEditorViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MemeEditorViewModel)
}