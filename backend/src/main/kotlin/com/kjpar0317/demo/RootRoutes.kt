package com.kjpar0317.demo

import com.kjpar0317.demo.control.TestController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.get

object RootRoutes {
    fun registRouter(app: Javalin) {
        app.routes {
            path("/api") {
                get("/test", TestController::getTest)
            }
        }
    }
}