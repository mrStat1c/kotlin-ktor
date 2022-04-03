package ru.otus.application

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        install(AutoHeadResponse)
        routing {
            route("/info") {

                get {
                    if (call.request.queryParameters.contains("id")) {
                        val id: String = call.request.queryParameters.get("id") ?: "null"
                        call.respondText { Json.encodeToString(GetInfo(id)) }
                    } else {
                        call.respondText { "Id is absent!" }
                    }
                }

                post {
                    val postName = Json.decodeFromString<PostName>(call.receiveText())
                    call.respondText { Json.encodeToString(ResGreeting("Hello, ${postName.name}"))}
                }
            }
        }
    }.start(wait = true)
}