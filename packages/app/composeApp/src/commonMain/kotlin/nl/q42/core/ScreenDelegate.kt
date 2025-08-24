package nl.q42.core

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import nl.q42.common.RequestHeader
import nl.q42.common.ScreenResponse
import nl.q42.common.core.AppIdentity
import nl.q42.common.core.Locale


private val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
            classDiscriminator = "type"
        })
    }
}

private const val SERVER_BASE_URI = "http://10.0.2.2:8080"

suspend fun fetchScreen(id: String): ScreenResponse? {
    return try {
        httpClient.get("$SERVER_BASE_URI/screen") {
            parameter("id", id)
            header(RequestHeader.HEADER_APP_LOCALE, Locale.NL_NL.value)
            header(RequestHeader.HEADER_APP_VERSION, 1)
            header(
                RequestHeader.HEADER_APP_IDENTITY,
                AppIdentity.calculateAppIdentity(Locale.NL_NL, 1)
            )
        }.body<ScreenResponse>()
    } catch (e: Exception) {
        // Handle exceptions (e.g., network error, parsing error)
        println("Error fetching screen: ${e.message}")
        null
    }
}