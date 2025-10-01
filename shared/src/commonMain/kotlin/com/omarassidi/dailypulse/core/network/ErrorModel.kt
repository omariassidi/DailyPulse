package com.omarassidi.dailypulse.core.network

import kotlinx.serialization.SerialName

data class ErrorModel(
    @SerialName("errorCode")
    val errorCode: String? = null,
    @SerialName("errorMessage")
    var message: String? = null
)

object ErrorCodes {
    const val InvalidEmail = "INVALID_EMAIL"
    const val InvalidPassword = "INVALID_PASSWORD"
    const val EmailNotVerified = "EMAIL_NOT_VERIFIED"
    const val UserAlreadyExists = "USER_ALREADY_EXISTS"
    const val UserNotFound = "USER_NOT_FOUND"
    const val InvalidCredentials = "INVALID_CREDENTIALS"
    const val ServiceNotAvailable = "Too many OTP requests"
    const val InvalidExpiredOtp = "Invalid or Expired OTP"
}