package com.omarassidi.dailypulse.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.json.Json

suspend inline fun <reified T : Any> apiCall(
    block: () -> HttpResponse,
    json: Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }
): Result<T> {
    return try {
        val response = block()

        return if (response.status.value in 200..299) {
            val body: T = response.body()
            Result.success(body)
        } else {
            if (response.status.value == 401) {
                Result.failure(AppFailure.Unauthorized)
            } else {
                val errorBody = response.bodyAsText()
                val error: ErrorModel? =
                    runCatching { json.decodeFromString<ErrorModel>(errorBody) }.getOrNull()
                if (error != null) {
                    Result.failure(AppFailure.Api(error))
                } else {
                    Result.failure(AppFailure.Unknown)
                }
            }
        }
    } catch (t: Throwable) {
        when (t) {
            is IOException -> Result.failure(AppFailure.Network)
            else -> Result.failure(AppFailure.Unknown)
        }
    }
}

suspend inline fun <reified T : Any> HttpClient.apiGet(
    endPoint: String,
    params: Map<String, String>
): Result<T> {
    return apiCall(block = {
        get(endPoint) {
            url {
                params.forEach {
                    parameters.append(it.key, it.value)
                }
            }
        }
    })
}

suspend inline fun <reified T : Any> HttpClient.apiPost(
    url: String
): Result<T> {
    return apiCall(block = {
        post(url)
    })
}

suspend inline fun <reified T : Any> HttpClient.apiPut(
    url: String
): Result<T> {
    return apiCall(block = {
        put(url)
    })
}

suspend inline fun <reified T : Any> HttpClient.apiDelete(
    httpClient: HttpClient,
    url: String
): Result<T> {
    return apiCall(block = {
        delete(url)
    })
}

