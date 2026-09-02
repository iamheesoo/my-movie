package com.heesoo.core.network

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import retrofit2.HttpException
import retrofit2.Response

@OptIn(InternalSerializationApi::class)
suspend inline fun <reified T : Any> handleApi(call: suspend () -> Response<T>): T {
    try {
        val json = Json {
            encodeDefaults = true
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        val response = call()
        if (response.isSuccessful) {
            return response.body() ?: json.decodeFromString(T::class.serializer(), "{}")
        } else {
            throw HttpException(response)
        }
    } catch (e: Exception) {
        throw e
    }
}