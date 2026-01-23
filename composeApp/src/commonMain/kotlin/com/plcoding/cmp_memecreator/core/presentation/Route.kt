package com.plcoding.cmp_memecreator.core.presentation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MemeGallery: Route

    @Serializable
    //even tho we cannot pass the drawable , we should always minimize the load on our navigation classes
    data class MemeEditor(
        val templateId: String
    ):Route



}