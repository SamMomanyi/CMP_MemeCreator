package com.plcoding.cmp_memecreator.di

import com.plcoding.cmp_memecreator.meme_editor.presentation.MemeEditorViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MemeEditorViewModel)
}