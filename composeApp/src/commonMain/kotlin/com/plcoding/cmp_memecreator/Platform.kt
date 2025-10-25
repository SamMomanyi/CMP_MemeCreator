package com.plcoding.cmp_memecreator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform