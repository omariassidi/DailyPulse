package com.omarassidi.dailypulse.about

expect class Platform {
    val osName: String
    val osVersion: String
    val deviceModel: String
    val density: Int
}