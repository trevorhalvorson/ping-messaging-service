import com.google.gson.Gson
import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.EmailException
import org.apache.commons.mail.SimpleEmail
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.application.log
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.locations.Locations
import org.jetbrains.ktor.locations.post
import org.jetbrains.ktor.locations.location
import org.jetbrains.ktor.request.receive
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Routing

@location("/")
class index()

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(GsonSupport)
    install(Routing) {
        post<index> {

            val gson = Gson()
            val req = gson.fromJson(call.receive<String>(), Request::class.java)

            val email = SimpleEmail()
            email.hostName = "smtp.googleemail.com"
            email.setSmtpPort(465)
            email.setAuthenticator(DefaultAuthenticator("useranme", "password"))
            email.isSSLOnConnect = true
            email.subject = "Your Custom APK"
            email.setMsg("Your custom build APK is attached to this email")
            email.addTo(req.email)

            try {
                email.send()
            } catch (e: EmailException) {
                log.debug("Failed to send email: ${e.message}")
            } finally {
                call.respond(req)
            }

        }
    }
}
