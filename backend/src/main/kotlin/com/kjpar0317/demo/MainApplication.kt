import com.kjpar0317.demo.RootRoutes
import io.javalin.Javalin

fun main() {
    val app = Javalin.create(/*config*/)
        .start(7070)

    RootRoutes.run { registRouter(app) }
}