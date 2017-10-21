import com.twilio.Twilio
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.locations.Locations
import io.ktor.locations.location
import io.ktor.routing.Routing
import mu.KLogging

@location("/ping")
class Ping

class App {

    companion object : KLogging()

    private val ACCOUNT_SID = System.getenv("ACCOUNT_SID")
    private val AUTH_TOKEN = System.getenv("AUTH_TOKEN")

    fun Application.main() {
        install(DefaultHeaders)
        install(CallLogging)
        install(Locations)
        install(ContentNegotiation) {
            register(ContentType.Application.Json, GsonConverter())
        }

        install(Routing) {
            ping()
        }

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN)
    }

}
