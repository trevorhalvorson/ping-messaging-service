import com.google.gson.Gson
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.run
import model.PingRequest
import model.PingResponse
import io.ktor.features.logInfo
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.routing.Route
import io.ktor.locations.post
import io.ktor.pipeline.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText

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