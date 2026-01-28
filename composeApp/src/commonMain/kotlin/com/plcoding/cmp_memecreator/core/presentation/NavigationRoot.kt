package com.plcoding.cmp_memecreator.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.plcoding.cmp_memecreator.meme_editor.presentation.MemeEditorScreen
import com.plcoding.cmp_memecreator.meme_editor.presentation.MemeEditorScreenRoot
import com.plcoding.cmp_memecreator.meme_gallery.presentation.MemeGalleryScreen

@Composable
fun NavigationRoot(){
    val navController = rememberNavController()

    //we create a interface to contain our screens
    NavHost(
        navController = navController,
        startDestination = Route.MemeGallery
    ){
        //here we have the routes they state what each route represents
        composable<Route.MemeGallery>{
            MemeGalleryScreen(
                onMemeTemplateSelected = { memeTemplate ->
                    navController.navigate(Route.MemeEditor(memeTemplate.id))
                }
            )
        }
        composable<Route.MemeEditor> {
            val templateId = it.toRoute<Route.MemeEditor>().templateId //how we get the data instance sent from one screen
            val template = remember(templateId){
                memeTemplates.first{ it.id == templateId } //we then use the id to find the drawable
            }
            //we call the MemeEditorScreenRoot and not MemeEditorScreen since that's the main entry point screen
            MemeEditorScreenRoot(
                template = template,
                onGoBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}