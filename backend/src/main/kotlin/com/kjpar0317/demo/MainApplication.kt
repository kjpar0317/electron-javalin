import com.kjpar0317.demo.registRouter
import io.javalin.Javalin

fun main() {
    val app = Javalin.create(/*config*/)
        .start(7070)

    registRouter(app)
}