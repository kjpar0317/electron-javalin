import com.kjpar0317.demo.config.registRouter
import com.kjpar0317.demo.config.registSecurity
import com.kjpar0317.demo.config.registException

import io.javalin.Javalin

fun main() {
    val app = Javalin.create(/*config*/)
        .start(7070)

    registRouter(app)
    registSecurity(app)
    registException(app)
}