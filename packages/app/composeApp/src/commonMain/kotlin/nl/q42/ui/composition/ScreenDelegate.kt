package nl.q42.ui.composition

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
import nl.q42.common.SDUIPolymorphicSerializer
import nl.q42.core.AppInstance
import nl.q42.local.getLocalDevelopmentUri

private val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
            serializersModule = SDUIPolymorphicSerializer
        })
    }
}

private val SERVER_BASE_URI = getLocalDevelopmentUri();

suspend fun fetchScreen(id: String, appInstance: AppInstance): ScreenResponse? {
    return try {
        httpClient.get("$SERVER_BASE_URI/screen") {
            parameter("id", id)
            header(RequestHeader.HEADER_APP_LOCALE, appInstance.locale.value)
            header(RequestHeader.HEADER_APP_VERSION, appInstance.version)
            header(RequestHeader.HEADER_APP_IDENTITY, appInstance.identity)
        }.body<ScreenResponse>()
    } catch (exception: Exception) {
        println("An error occurred whilst attempting to deserialize response: ${exception.message}")
        return null
    }
}