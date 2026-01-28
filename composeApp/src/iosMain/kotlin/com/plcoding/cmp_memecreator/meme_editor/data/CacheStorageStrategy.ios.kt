package com.plcoding.cmp_memecreator.meme_editor.data

import com.plcoding.cmp_memecreator.meme_editor.domain.SaveToStorageStrategy

actual class CacheStorageStrategy :
    SaveToStorageStrategy {
    actual override fun getFilePath(fileName: String): String {
        TODO("Not yet implemented")
    }
}