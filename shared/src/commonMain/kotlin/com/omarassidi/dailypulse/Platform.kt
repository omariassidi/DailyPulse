package com.omarassidi.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform