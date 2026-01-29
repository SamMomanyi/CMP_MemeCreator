package com.plcoding.cmp_memecreator.meme_editor.presentation.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

actual class PlatformShareSheet(
    private val context : Context
) {
    //Uri makes our App like a file provider and also gives temporary access to the file contents to another app
    actual suspend fun shareFile(filePath: String) {
        //we first create the file path then content Uri
        val file = File(filePath)

        //then a uri to pass the file
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
        //we then create an Intent which is like an envelope with content that we want to send (intention😂)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/jpeg"
            putExtra(Intent.EXTRA_STREAM,uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //If there is an instance of the app already running we call a new instance
        }

        //this is the final intent then will open our platform share sheet
        val chooser = Intent.createChooser(intent,null).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        context.startActivity(chooser)
    }
}