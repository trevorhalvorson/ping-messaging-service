import com.twilio.Twilio
import mu.KLogging
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.locations.Locations
import org.jetbrains.ktor.locations.location
import org.jetbrains.ktor.routing.Routing

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
        install(GsonSupport)
        install(Routing) {
            ping()
        }

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN)
    }

}
