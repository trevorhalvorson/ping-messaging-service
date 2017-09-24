import com.google.gson.Gson
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.run
import model.PingRequest
import model.PingResponse
import org.jetbrains.ktor.features.logInfo
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.locations.post
import org.jetbrains.ktor.request.receive
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.response.respondText

val TWILIO_NUMBER = System.getenv("TWILIO_NUMBER")

fun Route.ping() {
    post<Ping> {
        try {
            run(CommonPool) {
                val request = call.receive<PingRequest>()
                val message = sendMessage(request.phoneNumber, request.message)
                call.respond(PingResponse(true, message.sid))
            }
        } catch (e: Exception) {
            App.logger.error(call.request.logInfo(), e)
            call.respondText(
                    Gson().toJson(PingResponse(false, e.localizedMessage)),
                    ContentType.Application.Json,
                    HttpStatusCode.BadRequest
            )
        }
    }
}

private suspend fun sendMessage(phoneNumber: String, message: String): Message {
    return Message.creator(PhoneNumber(phoneNumber), PhoneNumber(TWILIO_NUMBER), message).create()
}