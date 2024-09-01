package com.idax.pokefight

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform