import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.locations.Locations
import org.jetbrains.ktor.locations.post
import org.jetbrains.ktor.locations.location
import org.jetbrains.ktor.routing.Routing

@location("/")
class index

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(GsonSupport)
    install(Routing) {
        post<index> {

        }
    }
}
