package com.plcoding.cmp_memecreator.meme_editor.data

import com.plcoding.cmp_memecreator.meme_editor.domain.SaveToStorageStrategy
import java.io.File
import android.content.Context

//we can write how the cached file path can be recieved
actual class CacheStorageStrategy (
    private val context : Context
):
    SaveToStorageStrategy {
    actual override fun getFilePath(fileName: String): String {
        return File(
            context.cacheDir,
            fileName).absolutePath
    }
}