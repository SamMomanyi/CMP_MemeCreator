package com.plcoding.cmp_memecreator.core.presentation

import cmpmemecreator.composeapp.generated.resources.Res
import cmpmemecreator.composeapp.generated.resources.allDrawableResources
import org.jetbrains.compose.resources.DrawableResource


//a helper class to get and pass the selected meme
data class MemeTemplate(
    val id : String,
    val drawable : DrawableResource
)

//this creates a list of meme templates,
//it collects all the drawable resources filter them to meme template then maps each item into the Meme
val memeTemplates = Res
    .allDrawableResources
    .filterKeys { it.startsWith("meme_template") }
    .map{ (key,value) ->
        MemeTemplate(
            id = key,
            drawable = value
        )
    }
