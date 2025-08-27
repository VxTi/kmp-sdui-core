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
import nl.q42.common.SDUIPolymorphicSerializer
import nl.q42.common.ScreenResponse
import nl.q42.common.core.QueryParameter
import nl.q42.common.core.ServerRoute
import nl.q42.local.getLocalDevelopmentUri

class ServerConnector(private val appInstance: AppInstance) {
    val serverBaseUrl = getLocalDevelopmentUri();

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                serializersModule = SDUIPolymorphicSerializer
            })
        }
    }

    suspend inline fun <reified T> fetch(
        appInstance: AppInstance,
        path: String,
        params: List<Pair<String, String>> = listOf()
    ): T where T : Any {
        return httpClient.get("$serverBaseUrl$path") {
            params.forEach { parameter(it.first, it.second) }
            header(RequestHeader.HEADER_APP_LOCALE, appInstance.locale.value)
            header(RequestHeader.HEADER_APP_VERSION, appInstance.version)
            header(RequestHeader.HEADER_APP_IDENTITY, appInstance.identity)
        }.body<T>()
    }

    suspend fun fetchScreen(screenId: String): ScreenResponse? {
        return try {
            fetch<ScreenResponse>(
                this.appInstance,
                ServerRoute.SCREEN,
                listOf(Pair(QueryParameter.SCREEN_IDENTIFIER, screenId))
            )
        } catch (exception: Exception) {
            println("An error occurred whilst attempting to deserialize response: ${exception.message}")
            return null
        }
    }
}