package com.plcoding.cmp_memecreator.meme_editor.domain

import androidx.compose.ui.unit.IntSize
import com.plcoding.cmp_memecreator.meme_editor.presentation.MemeText
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface MemeExporter {
    //we perform exporting memes as a background
    //we feed in the inputs we might need to export a function in a platform specific way
    @OptIn(ExperimentalUuidApi::class)
    suspend fun  exportMeme(
        //the fittest way to export an image is by using a byte array(an array of bites),this is the underlying image
        backgroundImageBytes : ByteArray,
        //WE ALSO need to be aware of all the positions of texts and their values e.g scaling
        memeText: List<MemeText>,
        templateSize : IntSize,
        fileName:String = "meme_${Uuid.random()}.jpg",
        saveToStorageStrategy: SaveToStorageStrategy
        ) : Result<String>
}