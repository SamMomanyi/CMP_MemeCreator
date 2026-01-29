package com.plcoding.cmp_memecreator.meme_editor.presentation.util

import platform.Foundation.NSURL
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

actual class PlatformShareSheet {
    actual  fun shareFile(filePath: String) {
        //we first create a fileURL
        val fileUrl = NSURL.fileURLWithPath(filePath)
        //we can share multiple items in IOS
        val itemsToShare = listOf(fileUrl)

        //this triggers the shareModalSheet
        val activityViewController = UIActivityViewController(
            activityItems = itemsToShare,
            applicationActivities = null
        )

        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController()
            ?: throw IllegalStateException("No root view controller found.")

        rootViewController.presentViewController(
            viewControllerToPresent = activityViewController,
            //when it's completed
            animated = true,
            completion = null
        )
    }
}