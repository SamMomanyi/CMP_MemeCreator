package com.plcoding.cmp_memecreator.meme_editor.data

import androidx.compose.ui.unit.IntSize
import com.plcoding.cmp_memecreator.meme_editor.domain.MemeExporter
import com.plcoding.cmp_memecreator.meme_editor.domain.SaveToStorageStrategy
import com.plcoding.cmp_memecreator.meme_editor.presentation.MemeText

actual class PlatformMemeExporter : MemeExporter {
    actual override suspend fun exportMeme(
        backgroundImageBytes: ByteArray,
        memeTexts: List<MemeText>,
        templateSize: IntSize,
        fileName: String,
        saveToStorageStrategy: SaveToStorageStrategy
    ): Result<String> {
        TODO("Not yet implemented")
    }
}